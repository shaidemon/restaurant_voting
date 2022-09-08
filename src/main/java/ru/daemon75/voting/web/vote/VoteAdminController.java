package ru.daemon75.voting.web.vote;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.daemon75.voting.model.Vote;
import ru.daemon75.voting.repository.VoteRepository;

import java.util.List;

@RestController
@RequestMapping(value = VoteAdminController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class VoteAdminController {
    static final String REST_URL = "/api/admin/votes";
    private final VoteRepository repository;

    @GetMapping()
    public List<Vote> getAll() {
        log.info("getAll");
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Vote> get(@PathVariable int id) {
        log.info("get vote id={}", id);
        return ResponseEntity.of(repository.findById(id));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete id={}", id);
        repository.delete(id);
    }
}
