package pl.adamorlowski.tablefootballratingapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.adamorlowski.tablefootballratingapi.dto.request.match.MatchCreateRequestDto;
import pl.adamorlowski.tablefootballratingapi.entity.match.Match;
import pl.adamorlowski.tablefootballratingapi.entity.Pair;
import pl.adamorlowski.tablefootballratingapi.exception.ResourceNotFoundException;
import pl.adamorlowski.tablefootballratingapi.repository.MatchRepository;
import pl.adamorlowski.tablefootballratingapi.service.MatchService;
import pl.adamorlowski.tablefootballratingapi.service.PairService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {
    private final MatchRepository matchRepository;
    private final PairService pairService;

    @Override
    public Match findById(UUID id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match", "id", id));
    }

    @Override
    public List<Match> findAll() {
        return matchRepository.findAll();
    }

    @Override
    public Match createMatch(MatchCreateRequestDto matchCreateRequestDto) {
        Pair pairA = pairService.getPairById(matchCreateRequestDto.getPairAId());
        Pair pairB = pairService.getPairById(matchCreateRequestDto.getPairBId());
        int scoreA = matchCreateRequestDto.getScoreA();
        int scoreB = matchCreateRequestDto.getScoreB();
        Match match = Match.builder()
                .pairA(pairA)
                .pairB(pairB)
                .scoreA(scoreA)
                .scoreB(scoreB)
                .createdAt(LocalDateTime.now())
                .matchRatingDifference(new ArrayList<>())
                .build();
        return matchRepository.save(match);
    }

    @Override
    public Match save(Match match) {
        return matchRepository.save(match);
    }
}
