package pl.adamorlowski.tablefootballratingapi.service;

import pl.adamorlowski.tablefootballratingapi.dto.request.match.MatchCreateRequestDto;
import pl.adamorlowski.tablefootballratingapi.entity.Pair;
import pl.adamorlowski.tablefootballratingapi.entity.match.Match;

import java.util.List;
import java.util.UUID;

public interface MatchService {
    Match findById(UUID id);
    List<Match> findAll();
    Match createMatch(MatchCreateRequestDto matchCreateRequestDto, Pair pairA, Pair pairB);

    Match save(Match match);
}
