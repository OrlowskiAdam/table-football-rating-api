package pl.adamorlowski.tablefootballratingapi.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.adamorlowski.tablefootballratingapi.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {}
