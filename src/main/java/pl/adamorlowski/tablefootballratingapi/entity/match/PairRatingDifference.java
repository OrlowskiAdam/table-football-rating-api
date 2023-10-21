package pl.adamorlowski.tablefootballratingapi.entity.match;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import pl.adamorlowski.tablefootballratingapi.entity.Pair;

@Entity
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@DiscriminatorValue("PAIR_RATING_DIFFERENCE")
public class PairRatingDifference extends MatchRatingDifference {
    @ManyToOne
    @JoinColumn(name = "pair_id")
    private Pair pair;
}
