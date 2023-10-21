package pl.adamorlowski.tablefootballratingapi.dto.request.pair;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter
@Data
public class CreatePairRequestDto {
    @Size(min = 2, max = 2)
    private Set<UUID> userIds;
}
