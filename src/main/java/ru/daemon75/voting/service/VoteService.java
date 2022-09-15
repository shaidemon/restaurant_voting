package ru.daemon75.voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.daemon75.voting.model.Vote;
import ru.daemon75.voting.repository.UserRepository;
import ru.daemon75.voting.repository.VoteRepository;

import static ru.daemon75.voting.util.Util.TODAY;
import static ru.daemon75.voting.util.VoteUtil.isTimeForVote;
import static ru.daemon75.voting.util.validation.ValidationUtil.assureIdConsistent;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository repository;
    private final UserRepository userRepository;

    @Transactional
    public Vote save(Vote vote, int userId) {
        vote.setUser(userRepository.getReferenceById(userId));
        Vote voteExist = repository.getExistToday(userId, TODAY);
        return voteExist == null ? repository.save(vote) : update(vote, voteExist.id(), userId);
    }

    public Vote update(Vote vote, int id, int userId) {
        assureIdConsistent(vote, id);
        repository.checkBelong(id, userId);
        if (isTimeForVote())
            return repository.save(vote);
        return null;
    }
}
