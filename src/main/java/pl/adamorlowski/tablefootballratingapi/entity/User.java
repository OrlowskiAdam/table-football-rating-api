package pl.adamorlowski.tablefootballratingapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "u_id")
    private UUID id;

    @NotBlank
    @Size(max = 40)
    @Column(name = "u_nickname")
    private String nickname;

    @NotBlank
    @Column(name = "u_name")
    private String name;

    @NotBlank
    @Size(max = 15)
    @JsonIgnore
    @Column(name = "u_username")
    private String username;

    @NotBlank
    @Size(max = 100)
    @JsonIgnore
    @Column(name = "u_password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "ur_u_id"),
            inverseJoinColumns = @JoinColumn(name = "ur_r_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy="user1", cascade={CascadeType.ALL})
    private List<Pairs> pairs1 = new ArrayList<>();

    @OneToMany(mappedBy="user2", cascade={CascadeType.ALL})
    private List<Pairs> pairs2 = new ArrayList<>();
}
