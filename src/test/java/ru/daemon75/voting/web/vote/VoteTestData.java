package ru.daemon75.voting.web.vote;

import ru.daemon75.voting.model.Vote;
import ru.daemon75.voting.web.MatcherFactory;

import java.time.LocalDate;

import static ru.daemon75.voting.web.restaurant.RestaurantTestData.*;

public class VoteTestData {

    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.
            usingIgnoringFieldsComparator(Vote.class, "user.password", "user.registered", "user.votes",
                    "restaurant.dishes", "restaurant.votes");

    public static final int VOTE1_ID_USER = 1;
    public static final int VOTE_ID_USER = 2;
    public static final int VOTE_ID_ADMIN = 3;
    public static final int NOT_FOUND = 100;

    public static final Vote vote1_user_prague = new Vote(VOTE1_ID_USER,
            LocalDate.now().minusDays(1), prague);
    public static final Vote vote_user_astoria = new Vote(VOTE_ID_USER,
            LocalDate.now(), astoria);
    public static final Vote vote_admin_seasons = new Vote(VOTE_ID_ADMIN,
            LocalDate.now(), seasons);

    public static Vote getNewDateNew() {
        return new Vote(null, LocalDate.now().plusDays(1), prague);
    }

    public static Vote getNewDateSame() {
        return new Vote(null, LocalDate.now(), prague);
    }

    public static Vote getUpdated() {
        return new Vote(VOTE_ID_USER, LocalDate.now(), prague);
    }

}
