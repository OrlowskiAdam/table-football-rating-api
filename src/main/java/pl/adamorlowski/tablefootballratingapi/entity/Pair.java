package pl.adamorlowski.tablefootballratingapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pairs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pair {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "p_id")
    private UUID id;


    @ManyToOne
    @JoinColumn(name="p_u1_id")
    User user1;

    @ManyToOne
    @JoinColumn(name="p_u2_id")
    User user2;

    @NotBlank
    @Column(name = "p_name")
    private String name;

    @OneToMany(mappedBy="pair1", cascade={CascadeType.ALL})
    private List<Match> matches1 = new ArrayList<>();

    @OneToMany(mappedBy="pair2", cascade={CascadeType.ALL})
    private List<Match> matches2 = new ArrayList<>();

    @OneToMany(mappedBy="pair", cascade={CascadeType.ALL})
    private List<Rating> ratings = new ArrayList<>();

}
