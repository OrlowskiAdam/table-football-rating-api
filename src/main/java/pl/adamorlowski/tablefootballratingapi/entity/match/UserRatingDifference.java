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
import pl.adamorlowski.tablefootballratingapi.entity.User;

@Entity
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@DiscriminatorValue("USER_RATING_DIFFERENCE")
public class UserRatingDifference extends MatchRatingDifference {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
