package pl.adamorlowski.tablefootballratingapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "ratings_history")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "rh_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name="rh_u_id")
    User user;

    @ManyToOne
    @JoinColumn(name="rh_p_id")
    Pair pair;

    @Column(name = "rh_old_value")
    private BigDecimal oldValue;

    @Column(name = "rh_new_value")
    private BigDecimal newValue;

    @Column(name = "rh_type")
    private String type;

    @ManyToOne
    @JoinColumn(name="rh_m_id")
    Match match;


}
