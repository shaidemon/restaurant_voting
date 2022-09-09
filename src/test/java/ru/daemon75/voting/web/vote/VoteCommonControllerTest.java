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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.daemon75.voting.util.JsonUtil.writeValue;
import static ru.daemon75.voting.web.user.UserTestData.USER_MAIL;
import static ru.daemon75.voting.web.user.UserTestData.user;
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
                .andExpect(VOTE_MATCHER.contentJson(vote1_user_prague, vote_user_astoria));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void createNewDateWithLocation() throws Exception {
        Vote newVote = getNewDateNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(newVote)))
                .andExpect(status().isCreated());
        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        newVote.setUser(user);
        VOTE_MATCHER.assertMatch(repository.getExisted(newId), newVote);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void createSameDateWithLocation() throws Exception {
        Vote newVote = getNewDateSame();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(newVote)))
                .andExpect(status().isCreated());
        Vote created = VOTE_MATCHER.readFromJson(action);
        int oldId = VOTE_ID_USER;
        newVote.setId(oldId);
        //Updating Vote or refuse - change CHECK_TIME in VoteUtil.class for various scenarios
        if (VoteUtil.isTimeForVote()) {
            VOTE_MATCHER.assertMatch(created, newVote);
            newVote.setUser(user);
            VOTE_MATCHER.assertMatch(repository.getExisted(oldId), newVote);
        } else {
            VOTE_MATCHER.assertMatch(created, vote_user_astoria);
            vote_user_astoria.setUser(user);
            VOTE_MATCHER.assertMatch(repository.getExisted(oldId), vote_user_astoria);
        }
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
                .andExpect(VoteUtil.isTimeForVote() ? status().isOk() : status().isForbidden());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + VOTE1_ID_USER))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(repository.findById(VOTE1_ID_USER).isPresent());
    }
}