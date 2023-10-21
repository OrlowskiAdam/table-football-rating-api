package pl.adamorlowski.tablefootballratingapi.entity.rating;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import pl.adamorlowski.tablefootballratingapi.entity.RatingType;

@Entity
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@DiscriminatorValue(RatingType.RatingTypeNames.GOLDA_RATING)
public class GoldaRating extends Rating {
}
