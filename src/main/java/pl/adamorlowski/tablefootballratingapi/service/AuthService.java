package pl.adamorlowski.tablefootballratingapi.service;

import pl.adamorlowski.tablefootballratingapi.dto.request.auth.LoginRequestDto;
import pl.adamorlowski.tablefootballratingapi.dto.request.auth.RegisterRequestDto;
import pl.adamorlowski.tablefootballratingapi.entity.User;
import pl.adamorlowski.tablefootballratingapi.security.UserPrincipal;

public interface AuthService {
    String login(LoginRequestDto loginRequestDto);
    User register(RegisterRequestDto registerRequestDto);
    User getUserFromPrincipal(UserPrincipal userPrincipal);
}
