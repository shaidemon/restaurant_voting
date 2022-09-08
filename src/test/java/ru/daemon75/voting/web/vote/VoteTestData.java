package ru.daemon75.voting.web.vote;

import ru.daemon75.voting.model.Vote;

import java.time.LocalDate;

import static ru.daemon75.voting.web.restaurant.RestaurantTestData.astoria;
import static ru.daemon75.voting.web.restaurant.RestaurantTestData.seasons;

public class VoteTestData {

    public static final int VOTE_ID_USER = 1;
    public static final int VOTE_ID_ADMIN = 2;
    public static final int NOT_FOUND = 100;

    public static final Vote vote_user_astoria = new Vote(VOTE_ID_USER, LocalDate.now(), astoria);
    public static final Vote vote_admin_seasons = new Vote(VOTE_ID_ADMIN, LocalDate.now(), seasons);

}
