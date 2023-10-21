package pl.adamorlowski.tablefootballratingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.adamorlowski.tablefootballratingapi.entity.Role;
import pl.adamorlowski.tablefootballratingapi.entity.RoleName;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(RoleName name);
}
