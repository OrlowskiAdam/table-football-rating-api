package pl.adamorlowski.tablefootballratingapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@DiscriminatorValue(value="GOLDA_RATING")
public class GoldaRating extends Rating{

}
