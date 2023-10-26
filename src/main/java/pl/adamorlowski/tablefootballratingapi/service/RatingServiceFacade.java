package pl.adamorlowski.tablefootballratingapi.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.adamorlowski.tablefootballratingapi.entity.Rating;

@Service
@RequiredArgsConstructor
public class RatingServiceFacade {
  private final List<RatingService> ratingServices;

  public List<Rating> createInitialRatings() {
    return ratingServices.stream()
        .map(RatingService::createInitialRating)
        .collect(Collectors.toList());
  }
}
