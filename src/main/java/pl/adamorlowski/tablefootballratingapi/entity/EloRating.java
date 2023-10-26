package pl.adamorlowski.tablefootballratingapi.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@DiscriminatorValue(value="ELO_RATING")
@SuperBuilder
@NoArgsConstructor
public class EloRating extends Rating {

}
