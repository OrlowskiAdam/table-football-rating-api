package pl.adamorlowski.tablefootballratingapi.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.adamorlowski.tablefootballratingapi.entity.Role;
import pl.adamorlowski.tablefootballratingapi.entity.RoleName;
import pl.adamorlowski.tablefootballratingapi.exception.ResourceNotFoundException;
import pl.adamorlowski.tablefootballratingapi.repository.RoleRepository;
import pl.adamorlowski.tablefootballratingapi.service.RoleService;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByName(RoleName name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "name", name));
    }

    @PostConstruct
    public void createDefaultRoles() {
        roleRepository.findByName(RoleName.ROLE_USER)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(RoleName.ROLE_USER);
                    return roleRepository.save(role);
                });

        roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(RoleName.ROLE_ADMIN);
                    return roleRepository.save(role);
                });
    }
}
