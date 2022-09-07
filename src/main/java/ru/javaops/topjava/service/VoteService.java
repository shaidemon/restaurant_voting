package ru.javaops.topjava.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava.model.Vote;
import ru.javaops.topjava.repository.UserRepository;
import ru.javaops.topjava.repository.VoteRepository;

import java.time.LocalDate;

import static ru.javaops.topjava.util.VoteUtil.isTimeForVote;

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
                .filter(v -> v.getDate_vote().toLocalDate().equals(LocalDate.now()))
                .findAny().orElse(null);
    }


}
