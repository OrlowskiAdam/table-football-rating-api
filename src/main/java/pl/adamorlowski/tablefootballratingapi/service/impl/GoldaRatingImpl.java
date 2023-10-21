package pl.adamorlowski.tablefootballratingapi.service.impl;

import org.springframework.stereotype.Service;
import pl.adamorlowski.tablefootballratingapi.entity.RatingType;
import pl.adamorlowski.tablefootballratingapi.entity.User;
import pl.adamorlowski.tablefootballratingapi.entity.match.Match;
import pl.adamorlowski.tablefootballratingapi.entity.Pair;
import pl.adamorlowski.tablefootballratingapi.entity.rating.GoldaRating;
import pl.adamorlowski.tablefootballratingapi.entity.rating.Rating;
import pl.adamorlowski.tablefootballratingapi.exception.ResourceNotFoundException;
import pl.adamorlowski.tablefootballratingapi.service.RatingService;

@Service
public class GoldaRatingImpl implements RatingService {
    @Override
    public GoldaRating createInitialRating() {
        return GoldaRating.builder()
                .value(50d)
                .build();
    }

    @Override
    public void calculateRating(Match match) {}

    @Override
    public GoldaRating getRating(Pair pair) {
        return pair.getRatings()
                .stream()
                .filter(rating -> rating instanceof GoldaRating)
                .findFirst()
                .map(rating -> (GoldaRating) rating)
                .orElseThrow(() -> new ResourceNotFoundException("Rating", "pairId", pair.getId()));
    }

    @Override
    public Rating getRating(User user) {
        return user.getRatings()
                .stream()
                .filter(rating -> rating instanceof GoldaRating)
                .findFirst()
                .map(rating -> (GoldaRating) rating)
                .orElseThrow(() -> new ResourceNotFoundException("Rating", "userId", user.getId()));
    }

    @Override
    public RatingType getRatingType() {
        return RatingType.GOLDA_RATING;
    }
}
