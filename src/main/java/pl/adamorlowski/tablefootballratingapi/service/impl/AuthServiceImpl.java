package pl.adamorlowski.tablefootballratingapi.service.impl;

import java.util.Collections;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.adamorlowski.tablefootballratingapi.dto.request.auth.LoginRequestDto;
import pl.adamorlowski.tablefootballratingapi.dto.request.auth.RegisterRequestDto;
import pl.adamorlowski.tablefootballratingapi.entity.Role;
import pl.adamorlowski.tablefootballratingapi.entity.RoleName;
import pl.adamorlowski.tablefootballratingapi.entity.User;
import pl.adamorlowski.tablefootballratingapi.exception.BadRequestException;
import pl.adamorlowski.tablefootballratingapi.repository.UserRepository;
import pl.adamorlowski.tablefootballratingapi.security.JwtTokenProvider;
import pl.adamorlowski.tablefootballratingapi.security.UserPrincipal;
import pl.adamorlowski.tablefootballratingapi.service.AuthService;
import pl.adamorlowski.tablefootballratingapi.service.RatingServiceFacade;
import pl.adamorlowski.tablefootballratingapi.service.RoleService;
import pl.adamorlowski.tablefootballratingapi.service.UserService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final UserRepository userRepository;
  private final RoleService roleService;
  private final UserService userService;
  private final RatingServiceFacade ratingServiceFacade;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider tokenProvider;
  @Override
  public String login(LoginRequestDto loginRequestDto) {
    User user = userService.getUserByUsername(loginRequestDto.getUsername());
    if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
      throw new BadRequestException("Invalid password");
    }
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginRequestDto.getUsername(),
            loginRequestDto.getPassword()
        )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return tokenProvider.generateToken(authentication);
  }

  @Override
  public User register(RegisterRequestDto registerRequestDto) {
    if (userRepository.existsByUsernameOrNickname(registerRequestDto.getUsername(), registerRequestDto.getNickname())) {
      throw new BadRequestException("Username or nickname is already taken");
    }
    User user = createUser(registerRequestDto);
    return userService.save(user);
  }

  @Override
  public User getUserFromPrincipal(UserPrincipal userPrincipal) {
    return userService.getUserById(userPrincipal.getId());
  }

  private User createUser(RegisterRequestDto registerRequestDto) {
    Role userRole = roleService.getRoleByName(RoleName.ROLE_USER);
    User user = User.builder()
        .username(registerRequestDto.getUsername())
        .password(passwordEncoder.encode(registerRequestDto.getPassword()))
        .nickname(registerRequestDto.getNickname())
        .name(registerRequestDto.getName())
        .ratings(ratingServiceFacade.createInitialRatings())
        .roles(new HashSet<>(Collections.singletonList(userRole)))
        .build();
    return userService.save(user);
  }
}
