package pl.adamorlowski.tablefootballratingapi.service;

import pl.adamorlowski.tablefootballratingapi.entity.User;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);
    List<User> getUsersByIds(Set<UUID> ids);
    User getUserByUsername(String username);
    User save(User user);
}
