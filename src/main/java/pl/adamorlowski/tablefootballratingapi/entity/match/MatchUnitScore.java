package pl.adamorlowski.tablefootballratingapi.entity.match;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "match_unit_scores")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchUnitScore {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Integer scoreA;
    private Integer scoreB;
}
