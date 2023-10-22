package pl.adamorlowski.tablefootballratingapi.dto.request.match;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class MatchUnitScoreRequestDto {
    private Integer scoreA;
    private Integer scoreB;
}
