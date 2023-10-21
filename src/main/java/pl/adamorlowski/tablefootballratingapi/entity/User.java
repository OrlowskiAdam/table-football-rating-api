package pl.adamorlowski.tablefootballratingapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.adamorlowski.tablefootballratingapi.entity.rating.Rating;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(max = 40)
    private String nickname;

    @NotBlank
    private String name;

    @NotBlank
    @Size(max = 15)
    @JsonIgnore
    private String username;

    @NotBlank
    @Size(max = 100)
    @JsonIgnore
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Rating> ratings = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
