package pl.adamorlowski.tablefootballratingapi.service;

import pl.adamorlowski.tablefootballratingapi.entity.Match;
import pl.adamorlowski.tablefootballratingapi.entity.Rating;

public interface RatingService {

  void calculate();

  void calculate(Match match);

  Rating createInitialRating();
  Rating save(Rating rating);

  String getRatingType();
}
