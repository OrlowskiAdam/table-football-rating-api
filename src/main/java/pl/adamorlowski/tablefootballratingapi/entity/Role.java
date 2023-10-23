package pl.adamorlowski.tablefootballratingapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import java.util.UUID;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "r_id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60, name = "r_name")
    private RoleName name;

}
