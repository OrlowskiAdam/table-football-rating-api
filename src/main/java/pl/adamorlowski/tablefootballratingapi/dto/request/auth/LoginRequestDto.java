package pl.adamorlowski.tablefootballratingapi.dto.request.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class LoginRequestDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
