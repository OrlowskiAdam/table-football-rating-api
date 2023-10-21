package pl.adamorlowski.tablefootballratingapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.adamorlowski.tablefootballratingapi.entity.User;
import pl.adamorlowski.tablefootballratingapi.exception.ResourceNotFoundException;
import pl.adamorlowski.tablefootballratingapi.repository.UserRepository;
import pl.adamorlowski.tablefootballratingapi.service.UserService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public List<User> getUsersByIds(Set<UUID> ids) {
        return userRepository.findAllById(ids);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
