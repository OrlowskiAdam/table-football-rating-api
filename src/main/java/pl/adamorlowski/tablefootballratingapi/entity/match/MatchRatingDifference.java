package pl.adamorlowski.tablefootballratingapi.entity.match;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.adamorlowski.tablefootballratingapi.entity.RatingType;

import java.util.UUID;

@Entity(name = "matches_rating_difference")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn(name = "match_rating_difference_type")
public abstract class MatchRatingDifference {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Double oldRating;
    private Double newRating;

    @Enumerated(EnumType.STRING)
    @NotNull
    private RatingType ratingType;
}
