package ru.daemon75.voting.web.vote;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
