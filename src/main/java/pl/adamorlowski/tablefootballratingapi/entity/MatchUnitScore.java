package pl.adamorlowski.tablefootballratingapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "match_unit_scores")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchUnitScore {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "mus_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name="mus_m_id")
    Match match;

    @Column(name = "mus_p1_score")
    private int pair1Score;

    @Column(name = "mus_p2_score")
    private int pair2Score;

    @Column(name = "mus_set_number")
    private int setNumber;

}
