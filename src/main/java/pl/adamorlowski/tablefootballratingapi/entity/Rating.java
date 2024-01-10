package pl.adamorlowski.tablefootballratingapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "ratings")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn(name = "ra_type")
public abstract class Rating {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "ra_id")
  private UUID id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "ra_u_id")
  @JsonIgnore
  User user;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "ra_p_id")
  @JsonIgnore
  Pair pair;

  @Column(name = "ra_value")
  private BigDecimal value;

  @Column(name = "ra_type", insertable = false, updatable = false)
  private String type;

  @Enumerated(EnumType.STRING)
  @Column(name = "ra_quarter")
  private QuarterType quarter;

  @Column(name = "ra_year")
  private Integer year;
}
