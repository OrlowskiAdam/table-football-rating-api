package pl.adamorlowski.tablefootballratingapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.adamorlowski.tablefootballratingapi.entity.RatingType;
import pl.adamorlowski.tablefootballratingapi.entity.User;
import pl.adamorlowski.tablefootballratingapi.entity.match.Match;
import pl.adamorlowski.tablefootballratingapi.entity.Pair;
import pl.adamorlowski.tablefootballratingapi.entity.match.PairRatingDifference;
import pl.adamorlowski.tablefootballratingapi.entity.match.UserRatingDifference;
import pl.adamorlowski.tablefootballratingapi.entity.rating.EloRating;
import pl.adamorlowski.tablefootballratingapi.exception.ResourceNotFoundException;
import pl.adamorlowski.tablefootballratingapi.repository.MatchRepository;
import pl.adamorlowski.tablefootballratingapi.repository.PairRepository;
import pl.adamorlowski.tablefootballratingapi.service.RatingService;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EloRatingImpl implements RatingService {
    private final MatchRepository matchRepository;
    private static final int EXPECTED_SCORE_DIVIDER = 500;
    private static final int NEW_RATING_MULTIPLIER = 100;

    @Override
    public EloRating createInitialRating() {
        return EloRating.builder()
                .value(1000d)
                .build();
    }

    @Override
    public void calculateRating(Match match) {
        this.calculatePairRating(match);
        this.calculateUsersRating(match);
        this.matchRepository.save(match);
    }

    @Override
    public EloRating getRating(Pair pair) {
        return pair.getRatings()
                .stream()
                .filter(rating -> rating instanceof EloRating)
                .findFirst()
                .map(rating -> (EloRating) rating)
                .orElseThrow(() -> new ResourceNotFoundException("Rating", "pairId", pair.getId()));
    }

    @Override
    public EloRating getRating(User user) {
        return user.getRatings()
                .stream()
                .filter(rating -> rating instanceof EloRating)
                .findFirst()
                .map(rating -> (EloRating) rating)
                .orElseThrow(() -> new ResourceNotFoundException("Rating", "userId", user.getId()));
    }

    @Override
    public RatingType getRatingType() {
        return RatingType.ELO_RATING;
    }

    private void calculatePairRating(Match match) {
        Pair winningPair = this.geWinningPair(match);
        EloRating winningPairRating = this.getRating(winningPair);
        Pair losingPair = this.getLosingPair(match);
        EloRating losingPairRating = this.getRating(losingPair);

        int totalGamesPlayed = this.getGamesCount(match);
        int losingPairTotalGoals = this.getLosingPairScore(match);

        double expectedWinningScore = this.getExpectedScoreForWinningPair(winningPairRating, losingPairRating, totalGamesPlayed);
        double actualWinningScore = this.getActualScoreForWinningPair(totalGamesPlayed, losingPairTotalGoals);
        double newWinningRating = this.getNewRating(winningPairRating, actualWinningScore, expectedWinningScore);

        double expectedLosingScore = this.getExpectedScoreForLosing(expectedWinningScore);
        double actualLosingScore = this.getActualScoreForLosingPair(totalGamesPlayed, losingPairTotalGoals);
        double newLosingRating = this.getNewRating(losingPairRating, actualLosingScore, expectedLosingScore);

        PairRatingDifference losingPairRatingDifference = this.createPairRatingDifference(losingPair, losingPairRating.getValue(), newLosingRating);
        PairRatingDifference winningPairRatingDifference = this.createPairRatingDifference(winningPair, winningPairRating.getValue(), newWinningRating);
        match.getMatchRatingDifference().add(losingPairRatingDifference);
        match.getMatchRatingDifference().add(winningPairRatingDifference);

        winningPairRating.setValue(newWinningRating);
        losingPairRating.setValue(newLosingRating);
    }

    private void calculateUsersRating(Match match) {
        Pair losingPair = this.getLosingPair(match);
        double losingPairTotalUserRating = losingPair.getUsers()
                .stream()
                .mapToDouble(user -> this.getRating(user).getValue())
                .sum();
        Set<User> winningPairUsers = this.geWinningPair(match).getUsers();
        winningPairUsers.forEach(user -> {
            double expectedUserScore = this.getExpectedScoreForWinningUser(this.getRating(user), losingPairTotalUserRating, this.getGamesCount(match));
            double actualWinningScore = this.getActualScoreForWinningPair(this.getGamesCount(match), this.getLosingPairScore(match));
            double newUserRating = this.getNewRating(this.getRating(user), actualWinningScore, expectedUserScore);
            UserRatingDifference userRatingDifference = createUserRatingDifference(user, this.getRating(user).getValue(), newUserRating);
            match.getMatchRatingDifference().add(userRatingDifference);
            this.getRating(user).setValue(newUserRating);
        });
        Set<User> losingPairUsers = this.getLosingPair(match).getUsers();
        losingPairUsers.forEach(user -> {
            double expectedScoreForWinningUser = this.getExpectedScoreForWinningUser(this.getRating(user), losingPairTotalUserRating, this.getGamesCount(match));
            double expectedUserScore = this.getExpectedScoreForLosing(expectedScoreForWinningUser);
            double actualLosingScore = this.getActualScoreForLosingPair(this.getGamesCount(match), this.getLosingPairScore(match));
            double newUserRating = this.getNewRating(this.getRating(user), actualLosingScore, expectedUserScore);
            UserRatingDifference userRatingDifference = createUserRatingDifference(user, this.getRating(user).getValue(), newUserRating);
            match.getMatchRatingDifference().add(userRatingDifference);
            this.getRating(user).setValue(newUserRating);
        });

    }

    /**
     * E(A) = 1 / (1 + 10 ^ ((R(B) - R(A)) / 500))
     *
     * @param winningPairRating rating wygranej pary po meczu
     * @param losingPairRating  rating przegranej pary po meczu
     * @return oczekiwany wynik dla wygranej pary w przedziale (0, 1)
     */
    private double getExpectedScoreForWinningPair(EloRating winningPairRating, EloRating losingPairRating, Integer totalGamesPlayed) {
        return 1 / (1 + Math.pow(totalGamesPlayed, (losingPairRating.getValue() - winningPairRating.getValue()) / EXPECTED_SCORE_DIVIDER));
    }

    private double getExpectedScoreForWinningUser(EloRating winningUserRating, Double losingPairTotalUserRating, Integer totalGamesPlayed) {
        return 1 / (1 + Math.pow(totalGamesPlayed, (winningUserRating.getValue() - (losingPairTotalUserRating / 2)) / EXPECTED_SCORE_DIVIDER));
    }

    /**
     * E(B) = 1 - E(A)
     *
     * @param winningPairExpectedScore oczekiwany wynik dla wygranej pary w przedziale (0, 1)
     * @return oczekiwany wynik dla przegranej pary w przedziale (0, 1)
     */
    private double getExpectedScoreForLosing(Double winningPairExpectedScore) {
        return 1 - winningPairExpectedScore;
    }

    /**
     * S(A) = 10 / (10 + i), gdzie i to ilość bramek strzelonych przez przegraną parę.
     * 10 - liczba możliwych bramek do strzelenia w meczu.
     *
     * @param totalGamesPlayed ilość rozegranych meczów
     * @param losingPairScore  ilość bramek strzelonych przez przegraną parę
     * @return rzeczywisty wynik dla wygranej pary w przedziale (0, 1)
     */
    private double getActualScoreForWinningPair(int totalGamesPlayed, double losingPairScore) {
        return totalGamesPlayed / (totalGamesPlayed + losingPairScore);
    }

    /**
     * S(B) = 1 - S(A)
     *
     * @param totalGamesPlayed ilość rozegranych meczów
     * @param losingPairScore  ilość bramek strzelonych przez przegraną parę
     * @return
     */
    private double getActualScoreForLosingPair(int totalGamesPlayed, double losingPairScore) {
        return 1 - this.getActualScoreForWinningPair(totalGamesPlayed, losingPairScore);
    }

    /**
     * R'(B) = R(B) - NEW_RATING_MULTIPLIER * (1 - E(A))
     *
     * @param pairRating        rating pary przed meczem
     * @param actualPairScore   oczekiwany wynik dla pary w przedziale (0, 1)
     * @param expectedPairScore rzeczywisty wynik dla pary w przedziale (0, 1)
     * @return
     */
    private double getNewRating(EloRating pairRating, double actualPairScore, double expectedPairScore) {
        double newRating = pairRating.getValue() + NEW_RATING_MULTIPLIER * (actualPairScore - expectedPairScore);
        if (newRating <= 0) {
            return 0;
        }
        return newRating;
    }
}
