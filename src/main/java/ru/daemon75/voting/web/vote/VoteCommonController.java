package ru.daemon75.voting.web.vote;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.daemon75.voting.model.Vote;
import ru.daemon75.voting.repository.RestaurantRepository;
import ru.daemon75.voting.repository.VoteRepository;
import ru.daemon75.voting.service.VoteService;
import ru.daemon75.voting.util.VoteUtil;
import ru.daemon75.voting.web.AuthUser;

import java.net.URI;
import java.util.List;

import static ru.daemon75.voting.util.validation.ValidationUtil.assureIdConsistent;
import static ru.daemon75.voting.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = VoteCommonController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class VoteCommonController {
    static final String REST_URL = "/api/votes";
    private final VoteRepository repository;
    private final RestaurantRepository restaurantRepository;
    private final VoteService service;

    @GetMapping
    public List<Vote> get(@AuthenticationPrincipal AuthUser authUser) {
        int userId = authUser.id();
        log.info("getAll for user id={}", userId);
        return repository.getAllByUser_Id(userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@RequestBody Vote vote, @AuthenticationPrincipal AuthUser authUser) {
        int userId = authUser.id();
        log.info("create {} with user id={}", vote, userId);
        checkNew(vote);
        Vote counted = service.save(vote, userId);
        URI uriOfResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(counted.getId()).toUri();
        return ResponseEntity.created(uriOfResource).body(counted);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> update(@AuthenticationPrincipal AuthUser authUser, @RequestBody Vote vote, @PathVariable int id) {
        int userId = authUser.id();
        log.info("update {} for user id={}", vote, userId);
        assureIdConsistent(vote, id);
        repository.checkBelong(id, userId);
        if (VoteUtil.isTimeForVote()) {
            repository.save(vote);
            return ResponseEntity.ok().build();
        } else return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        int userId = authUser.id();
        log.info("delete id={} for user id={}", id, userId);
        Vote vote = repository.checkBelong(id, userId);
        repository.delete(vote);
    }

}
