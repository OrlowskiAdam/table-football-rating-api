package pl.adamorlowski.tablefootballratingapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.adamorlowski.tablefootballratingapi.dto.request.pair.CreatePairRequestDto;
import pl.adamorlowski.tablefootballratingapi.entity.Pair;
import pl.adamorlowski.tablefootballratingapi.entity.User;
import pl.adamorlowski.tablefootballratingapi.exception.BadRequestException;
import pl.adamorlowski.tablefootballratingapi.exception.ResourceNotFoundException;
import pl.adamorlowski.tablefootballratingapi.repository.PairRepository;
import pl.adamorlowski.tablefootballratingapi.service.PairService;
import pl.adamorlowski.tablefootballratingapi.service.RatingServiceFacade;
import pl.adamorlowski.tablefootballratingapi.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PairServiceImpl implements PairService {
    private final UserService userService;
    private final RatingServiceFacade ratingServiceFacade;
    private final PairRepository pairRepository;

    @Override
    public Pair createPair(CreatePairRequestDto createPairRequestDto) {
        List<User> users = userService.getUsersByIds(createPairRequestDto.getUserIds());
        if (users.size() != 2)
            throw new BadRequestException("Pair must have exactly 2 different users.");
        // TODO: check if pair with given users already exists
        Pair pair = Pair.builder()
                .ratings(ratingServiceFacade.createInitialRatings())
                .matches(new HashSet<>())
                .users(new HashSet<>(users))
                .build();
        return pairRepository.save(pair);
    }

    @Override
    public Pair getPairById(UUID id) {
        return pairRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pair", "id", id));
    }

    @Override
    public Pair save(Pair pair) {
        return pairRepository.save(pair);
    }

    @Override
    public List<Pair> saveAll(List<Pair> pairs) {
        return pairRepository.saveAll(pairs);
    }
}
