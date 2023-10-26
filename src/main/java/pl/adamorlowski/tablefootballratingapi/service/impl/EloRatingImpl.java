package pl.adamorlowski.tablefootballratingapi.service.impl;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.adamorlowski.tablefootballratingapi.entity.EloRating;
import pl.adamorlowski.tablefootballratingapi.entity.Match;
import pl.adamorlowski.tablefootballratingapi.entity.Rating;
import pl.adamorlowski.tablefootballratingapi.repository.RatingRepository;
import pl.adamorlowski.tablefootballratingapi.service.RatingService;
import pl.adamorlowski.tablefootballratingapi.utils.QuarterUtils;

@Service
@RequiredArgsConstructor
public class EloRatingImpl implements RatingService {
  private final RatingRepository ratingRepository;

  @Override
  public void calculate() {

  }

  @Override
  public void calculate(Match match) {

  }

  @Override
  public Rating createInitialRating() {
    return EloRating.builder()
        .value(new BigDecimal(1000))
        .quarter(QuarterUtils.getQuarter())
        .year(QuarterUtils.getYear())
        .build();
  }

  @Override
  public Rating save(Rating rating) {
    return ratingRepository.save(rating);
  }

  @Override
  public String getRatingType() {
    return "ELO_RATING";
  }
}
