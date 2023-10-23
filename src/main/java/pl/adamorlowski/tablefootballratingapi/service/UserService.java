package pl.adamorlowski.tablefootballratingapi.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import pl.adamorlowski.tablefootballratingapi.entity.User;

public interface UserService {
  User getUserById(UUID id);
  List<User> getUsersByIds(Set<UUID> ids);
  User getUserByUsername(String username);
  User save(User user);

  List<User> findAll();
}
