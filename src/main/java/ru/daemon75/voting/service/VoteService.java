package ru.daemon75.voting.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.daemon75.voting.model.Vote;
import ru.daemon75.voting.repository.UserRepository;
import ru.daemon75.voting.repository.VoteRepository;

import java.time.LocalDate;

import static ru.daemon75.voting.util.VoteUtil.isTimeForVote;

@Service
@Slf4j
@AllArgsConstructor
public class VoteService {
    private final VoteRepository repository;
    private final UserRepository userRepository;

    @Transactional
    public Vote save(Vote vote, int userId) {
        vote.setUser(userRepository.getExisted(userId));
        Vote voteExist = getVoteExist(userId);
        if (isTimeForVote() && voteExist != null) {
            updateExist(vote, voteExist);
        }
        return voteExist == null ? repository.save(vote) : voteExist;
    }

    private void updateExist(Vote vote, Vote voteExist) {
        log.info("change voting, vote with id={}", voteExist.id());
        voteExist.setRestaurant(vote.getRestaurant());
        repository.save(voteExist);
    }

    private Vote getVoteExist(int userId) {
        return repository.getAllByUser_Id(userId).stream()
                .filter(v -> v.getDate_vote().equals(LocalDate.now()))
                .findAny().orElse(null);
    }


}
