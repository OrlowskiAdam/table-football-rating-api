package pl.adamorlowski.tablefootballratingapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.adamorlowski.tablefootballratingapi.dto.request.pair.CreatePairRequestDto;
import pl.adamorlowski.tablefootballratingapi.entity.Pair;
import pl.adamorlowski.tablefootballratingapi.service.PairService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pair")
public class PairController {
    private final PairService pairService;

    @Secured("ROLE_USER")
    @PostMapping
    public ResponseEntity<Pair> createPair(@Validated @RequestBody CreatePairRequestDto createPairRequestDto) {
        Pair pair = pairService.createPair(createPairRequestDto);
        return ResponseEntity.ok(pair);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pair> getPair(@PathVariable("id") UUID id) {
        Pair pair = pairService.getPairById(id);
        return ResponseEntity.ok(pair);
    }
}
