package pl.adamorlowski.tablefootballratingapi.service;

import pl.adamorlowski.tablefootballratingapi.entity.Role;
import pl.adamorlowski.tablefootballratingapi.entity.RoleName;

public interface RoleService {
    Role getRoleByName(RoleName name);
}
