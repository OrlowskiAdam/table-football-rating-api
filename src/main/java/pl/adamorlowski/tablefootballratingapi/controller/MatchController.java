package pl.adamorlowski.tablefootballratingapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.adamorlowski.tablefootballratingapi.dto.request.match.MatchCreateRequestDto;
import pl.adamorlowski.tablefootballratingapi.entity.Pair;
import pl.adamorlowski.tablefootballratingapi.entity.match.Match;
import pl.adamorlowski.tablefootballratingapi.service.MatchService;
import pl.adamorlowski.tablefootballratingapi.service.PairService;
import pl.adamorlowski.tablefootballratingapi.service.RatingServiceFacade;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/match")
public class MatchController {
    private final MatchService matchService;
    private final RatingServiceFacade ratingServiceFacade;
    private final PairService pairService;

    @Secured("ROLE_USER")
    @PostMapping
    public ResponseEntity<Match> createMatch(@Validated @RequestBody MatchCreateRequestDto matchCreateRequestDto) {
        Pair pairA = pairService.createPair(matchCreateRequestDto.getPairA());
        Pair pairB = pairService.createPair(matchCreateRequestDto.getPairB());
        Match match = matchService.createMatch(matchCreateRequestDto, pairA, pairB);
        ratingServiceFacade.calculateRating(match);
        return ResponseEntity.ok(match);
    }

    @GetMapping
    public ResponseEntity<List<Match>> getMatches() {
        List<Match> matches = matchService.findAll();
        return ResponseEntity.ok(matches);
    }
}
