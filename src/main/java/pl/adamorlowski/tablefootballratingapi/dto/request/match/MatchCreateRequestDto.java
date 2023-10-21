package pl.adamorlowski.tablefootballratingapi.dto.request.match;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
public class MatchCreateRequestDto {
    @NotNull(message = "Pair ID is required")
    private UUID pairAId;
    @NotNull(message = "Pair ID is required")
    private UUID pairBId;

    @NotNull(message = "Score is required")
    private Integer scoreA;
    @NotNull(message = "Score is required")
    private Integer scoreB;
}
