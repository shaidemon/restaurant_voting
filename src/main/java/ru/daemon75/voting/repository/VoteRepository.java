package ru.daemon75.voting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.daemon75.voting.error.DataConflictException;
import ru.daemon75.voting.model.Vote;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    @Query("SELECT v FROM Vote v WHERE v.id = :id and v.user.id = :userId")
    Optional<Vote> getByUser(int id, int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=?1")
    List<Vote> getAllByUser_Id(int userId);

    default Vote checkBelong(int id, int userId) {
        return getByUser(id, userId).orElseThrow(
                () -> new DataConflictException("Vote id=" + id + " doesn't belong to User id=" + userId));
    }
}
