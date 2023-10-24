package pl.adamorlowski.tablefootballratingapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "matches")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "m_id")
    private UUID id;


    @ManyToOne
    @JoinColumn(name="m_p1_id")
    Pair pair1;

    @ManyToOne
    @JoinColumn(name="m_p2_id")
    Pair pair2;

    @Column(name = "m_match_date")
    private LocalDateTime matchDate;

    @Column(name = "m_p1_score")
    private int pair1Score;

    @Column(name = "m_p2_score")
    private int pair2Score;

    @OneToMany(mappedBy="match", cascade={CascadeType.ALL})
    private List<MatchUnitScore> matchUnitScores = new ArrayList<>();

    @OneToMany(mappedBy="match", cascade={CascadeType.ALL})
    private List<RatingHistory> ratingsHistory = new ArrayList<>();

}
