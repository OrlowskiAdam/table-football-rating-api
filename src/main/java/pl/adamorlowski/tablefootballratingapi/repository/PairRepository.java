package pl.adamorlowski.tablefootballratingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.adamorlowski.tablefootballratingapi.entity.Pair;

import java.util.UUID;

@Repository
public interface PairRepository extends JpaRepository<Pair, UUID> {
}
