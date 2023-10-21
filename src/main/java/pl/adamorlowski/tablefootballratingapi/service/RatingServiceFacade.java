package pl.adamorlowski.tablefootballratingapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.adamorlowski.tablefootballratingapi.entity.match.Match;
import pl.adamorlowski.tablefootballratingapi.entity.rating.Rating;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
public class RatingServiceFacade implements BaseRatingService {
    private final List<RatingService> ratingServices;

    public Set<Rating> createInitialRatings() {
        return ratingServices.stream()
                .map(RatingService::createInitialRating)
                .collect(toSet());
    }

    public void calculateRating(Match match) {
        ratingServices.forEach(ratingService -> ratingService.calculateRating(match));
    }
}
