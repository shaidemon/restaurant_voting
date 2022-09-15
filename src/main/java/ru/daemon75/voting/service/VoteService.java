package ru.daemon75.voting.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.daemon75.voting.model.Vote;
import ru.daemon75.voting.repository.UserRepository;
import ru.daemon75.voting.repository.VoteRepository;

import static ru.daemon75.voting.util.Util.TODAY;
import static ru.daemon75.voting.util.VoteUtil.isTimeForVote;

@Service
@Slf4j
@AllArgsConstructor
public class VoteService {
    private final VoteRepository repository;
    private final UserRepository userRepository;

    @Transactional
    public Vote save(Vote vote, int userId) {
        vote.setUser(userRepository.getReferenceById(userId));
        Vote voteExist = repository.getExistToday(userId, TODAY);
        return voteExist == null ? repository.save(vote) : null;
    }

    public Vote update(Vote vote) {
        if (isTimeForVote())
            return repository.save(vote);
        return null;
    }

//    private void updateExist(Vote vote, Vote voteExist) {
//        log.info("change voting, vote with id={}", vote.id());
//        voteExist.setRestaurantId(vote.getRestaurantId());
//        repository.save(voteExist);
//    }

//    private Vote getVoteExist(int userId, LocalDate dateVote) {
//        return repository.getAllByUser_Id(userId).stream()
//                .filter(v -> v.getDate_vote().equals(dateVote))
//                .findAny().orElse(null);
//    }


}
