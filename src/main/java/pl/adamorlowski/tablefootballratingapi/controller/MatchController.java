package pl.adamorlowski.tablefootballratingapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.adamorlowski.tablefootballratingapi.dto.request.match.MatchCreateRequestDto;
import pl.adamorlowski.tablefootballratingapi.entity.match.Match;
import pl.adamorlowski.tablefootballratingapi.service.MatchService;
import pl.adamorlowski.tablefootballratingapi.service.RatingServiceFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/match")
public class MatchController {
    private final MatchService matchService;
    private final RatingServiceFacade ratingServiceFacade;

    @Secured("ROLE_USER")
    @PostMapping
    public ResponseEntity<Match> createMatch(@Validated @RequestBody MatchCreateRequestDto matchCreateRequestDto) {
        Match match = matchService.createMatch(matchCreateRequestDto);
        ratingServiceFacade.calculateRating(match);
        return ResponseEntity.ok(match);
    }
}
