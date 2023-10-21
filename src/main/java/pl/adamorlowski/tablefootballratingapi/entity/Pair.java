package pl.adamorlowski.tablefootballratingapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.adamorlowski.tablefootballratingapi.entity.match.Match;
import pl.adamorlowski.tablefootballratingapi.entity.rating.Rating;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "pairs")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pair {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToMany
    @Size(max = 2)
    Set<User> users = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    Set<Rating> ratings = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    Set<Match> matches = new HashSet<>();
}
