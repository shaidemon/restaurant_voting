package ru.daemon75.voting.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.daemon75.voting.model.Vote;
import ru.daemon75.voting.repository.VoteRepository;
import ru.daemon75.voting.util.VoteUtil;
import ru.daemon75.voting.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.daemon75.voting.util.JsonUtil.writeValue;
import static ru.daemon75.voting.web.user.UserTestData.*;
import static ru.daemon75.voting.web.vote.VoteTestData.getNew;
import static ru.daemon75.voting.web.vote.VoteTestData.getUpdated;
import static ru.daemon75.voting.web.vote.VoteTestData.*;

class VoteCommonControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteCommonController.REST_URL + '/';

    @Autowired
    private VoteRepository repository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(vote1_user_astoria, vote_user_astoria));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createNewDateWithLocation() throws Exception {
        Vote newVote = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(newVote)))
                .andExpect(status().isCreated());
        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        newVote.setUser(user);
        VOTE_MATCHER.assertMatch(created, newVote);
        newVote.setUser(user);
        VOTE_MATCHER.assertMatch(repository.getExisted(newId), newVote);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void createAgain() throws Exception {
        Vote newVote = getNew();
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(newVote)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void update() throws Exception {
        Vote updated = getUpdated();
        updated.setId(null);
        perform(MockMvcRequestBuilders.put(REST_URL + VOTE_ID_USER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andDo(print())
                // Vote updated or refused - change CHECK_TIME in VoteUtil.class for various scenarios
                .andExpect(VoteUtil.isTimeForVote() ? status().isOk() : status().isUnprocessableEntity());
    }
}