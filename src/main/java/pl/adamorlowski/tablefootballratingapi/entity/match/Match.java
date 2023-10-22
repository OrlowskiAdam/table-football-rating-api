package pl.adamorlowski.tablefootballratingapi.entity.match;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.adamorlowski.tablefootballratingapi.entity.Pair;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "matches")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    @Min(0)
    @Max(4)
    private Integer scoreA;

    @Min(0)
    @Max(4)
    private Integer scoreB;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pair_a_id")
    private Pair pairA;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pair_b_id")
    private Pair pairB;

    @OneToMany(cascade = CascadeType.ALL)
    private List<MatchRatingDifference> matchRatingDifference;

    @OneToMany(cascade = CascadeType.ALL)
    private List<MatchUnitScore> unitScores;
}
