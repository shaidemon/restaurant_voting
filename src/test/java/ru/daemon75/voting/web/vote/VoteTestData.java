package ru.daemon75.voting.web.vote;

import ru.daemon75.voting.model.Vote;
import ru.daemon75.voting.web.MatcherFactory;
import ru.daemon75.voting.web.user.UserTestData;

import static ru.daemon75.voting.util.Util.TODAY;
import static ru.daemon75.voting.util.Util.YESTERDAY;
import static ru.daemon75.voting.web.restaurant.RestaurantTestData.ASTORIA_ID;
import static ru.daemon75.voting.web.restaurant.RestaurantTestData.PRAGUE_ID;

public class VoteTestData {

    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.
            usingIgnoringFieldsComparator(Vote.class, "user",
//            usingIgnoringFieldsComparator(Vote.class, "user.password", "user.registered", "user.votes",
                    "restaurant.MENU_ITEMS", "restaurant.votes");

    public static final int VOTE1_ID_USER = 1;
    public static final int VOTE_ID_USER = 2;
    public static final int VOTE_ID_ADMIN = 3;
    public static final int NOT_FOUND = 100;

    public static final Vote vote1_user_astoria = new Vote(VOTE1_ID_USER, YESTERDAY, ASTORIA_ID);
    public static final Vote vote_user_astoria = new Vote(VOTE_ID_USER, TODAY, ASTORIA_ID);
//    public static final Vote vote_admin_seasons = new Vote(VOTE_ID_ADMIN, TODAY, SEASONS_ID);

    static {
        vote_user_astoria.setUser(UserTestData.user);
        vote1_user_astoria.setUser(UserTestData.user);
//        vote_admin_seasons.setUser(UserTestData.admin);
    }

    public static Vote getNew() {
        return new Vote(null, TODAY, PRAGUE_ID);
    }

//    public static Vote getNewDateSame() {
//        return new Vote(null, TODAY, PRAGUE_ID);
//    }

    public static Vote getUpdated() {
        return new Vote(VOTE_ID_USER, TODAY, PRAGUE_ID);
    }

}
