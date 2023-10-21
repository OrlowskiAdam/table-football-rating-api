package pl.adamorlowski.tablefootballratingapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.adamorlowski.tablefootballratingapi.dto.request.auth.LoginRequestDto;
import pl.adamorlowski.tablefootballratingapi.dto.request.auth.RegisterRequestDto;
import pl.adamorlowski.tablefootballratingapi.entity.User;
import pl.adamorlowski.tablefootballratingapi.security.CurrentUser;
import pl.adamorlowski.tablefootballratingapi.security.JwtTokenProvider;
import pl.adamorlowski.tablefootballratingapi.security.UserPrincipal;
import pl.adamorlowski.tablefootballratingapi.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtTokenProvider tokenProvider;

    @GetMapping("/me")
    public ResponseEntity<User> me(@CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(authService.getUserFromPrincipal(userPrincipal));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        String jwt = authService.login(loginRequestDto);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Validated @RequestBody RegisterRequestDto registerRequestDto) {
        User newUser = authService.register(
                registerRequestDto);
        String jwt = tokenProvider.generateToken(newUser);
        return ResponseEntity.ok(jwt);
    }

}
