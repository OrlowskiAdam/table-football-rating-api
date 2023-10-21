package pl.adamorlowski.tablefootballratingapi.service;

import pl.adamorlowski.tablefootballratingapi.entity.Pair;
import pl.adamorlowski.tablefootballratingapi.entity.RatingType;
import pl.adamorlowski.tablefootballratingapi.entity.User;
import pl.adamorlowski.tablefootballratingapi.entity.match.Match;
import pl.adamorlowski.tablefootballratingapi.entity.match.PairRatingDifference;
import pl.adamorlowski.tablefootballratingapi.entity.match.UserRatingDifference;
import pl.adamorlowski.tablefootballratingapi.entity.rating.Rating;
import pl.adamorlowski.tablefootballratingapi.exception.BadRequestException;

import java.util.Objects;

public interface RatingService extends BaseRatingService {
    Rating createInitialRating();
    void calculateRating(Match match);
    default Pair geWinningPair(Match match) {
        this.validateMatchScores(match);
        if (match.getScoreA() > match.getScoreB()) {
            return match.getPairA();
        }
        return match.getPairB();
    }
    default Pair getLosingPair(Match match) {
        this.validateMatchScores(match);
        if (match.getScoreA() < match.getScoreB()) {
            return match.getPairA();
        }
        return match.getPairB();
    };

    Rating getRating(Pair pair);
    Rating getRating(User user);
    RatingType getRatingType();

    default Integer getGamesCount(Match match) {
        return match.getScoreA() + match.getScoreB();
    }

    default void validateMatchScores(Match match) {
        // wynik jest równy = mecz nie może zakończyć się remisem
        if (Objects.equals(match.getScoreA(), match.getScoreB()))
            throw new BadRequestException("Match cannot end with a draw.");
    }

    default int getLosingPairScore(Match match) {
        return Math.min(match.getScoreA(), match.getScoreB());
    }

    default PairRatingDifference createPairRatingDifference(Pair pair, Double oldRating, Double newRating) {
        return PairRatingDifference.builder()
                .pair(pair)
                .oldRating(oldRating)
                .newRating(newRating)
                .ratingType(this.getRatingType())
                .build();
    }

    default UserRatingDifference createUserRatingDifference(User user, Double oldRating, Double newRating) {
        return UserRatingDifference.builder()
                .user(user)
                .oldRating(oldRating)
                .newRating(newRating)
                .ratingType(this.getRatingType())
                .build();
    }
}
