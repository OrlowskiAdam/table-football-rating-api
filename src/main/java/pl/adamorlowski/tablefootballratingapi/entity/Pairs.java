package pl.adamorlowski.tablefootballratingapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "pairs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pairs {
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

}
