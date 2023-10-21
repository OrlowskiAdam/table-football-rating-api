package pl.adamorlowski.tablefootballratingapi.service;

import pl.adamorlowski.tablefootballratingapi.dto.request.pair.CreatePairRequestDto;
import pl.adamorlowski.tablefootballratingapi.entity.Pair;

import java.util.List;
import java.util.UUID;

public interface PairService {
    Pair createPair(CreatePairRequestDto createPairRequestDto);

    Pair getPairById(UUID id);

    Pair save(Pair pair);
    List<Pair> saveAll(List<Pair> pairs);
}
