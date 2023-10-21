package pl.adamorlowski.tablefootballratingapi.dto.request.auth;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterRequestDto {
    @NotNull(message = "Username cannot be null")
    private String username;
    @NotNull(message = "Password cannot be null")
    private String password;
    @NotNull(message = "Nickname cannot be null")
    private String nickname;
    @NotNull(message = "Name cannot be null")
    private String name;
}
