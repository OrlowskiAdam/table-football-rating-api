package pl.adamorlowski.tablefootballratingapi.dto.request.match;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import pl.adamorlowski.tablefootballratingapi.entity.User;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
public class MatchCreateRequestDto {
    @NotNull(message = "Pair A is required")
    @Size(min = 2, max = 2, message = "Pair ID must contain 2 elements")
    private Set<UUID> pairA;
    @NotNull(message = "Pair B is required")
    @Size(min = 2, max = 2, message = "Pair ID must contain 2 elements")
    private Set<UUID> pairB;

    @NotNull(message = "Score is required")
    private Integer scoreA;
    @NotNull(message = "Score is required")
    private Integer scoreB;

    private List<MatchUnitScoreRequestDto> unitScores;
}
