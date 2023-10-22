package pl.adamorlowski.tablefootballratingapi.service;

import pl.adamorlowski.tablefootballratingapi.dto.request.pair.CreatePairRequestDto;
import pl.adamorlowski.tablefootballratingapi.entity.Pair;
import pl.adamorlowski.tablefootballratingapi.entity.User;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface PairService {
    Pair createPair(CreatePairRequestDto createPairRequestDto);
    Pair createPair(Set<UUID> userIds);

    Pair getPairById(UUID id);
    List<Pair> getPairs();

    Pair save(Pair pair);
    List<Pair> saveAll(List<Pair> pairs);

    Pair getPairByUsers(List<User> users);

    Pair getPairByUsers(Set<User> users);

    boolean pairExists(List<User> users);
}
