package ru.daemon75.voting.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.daemon75.voting.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.daemon75.voting.web.user.UserTestData.ADMIN_MAIL;
import static ru.daemon75.voting.web.vote.VoteTestData.*;

class VoteAdminControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteAdminController.REST_URL + '/';


    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(vote1_user_astoria, vote_user_astoria));
    }

//    @Test
//    @WithUserDetails(value = ADMIN_MAIL)
//    void get() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL + VOTE_ID_USER))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(VOTE_MATCHER.contentJson(vote_user_astoria));
//    }

//    @Test
//    @WithUserDetails(value = ADMIN_MAIL)
//    void getNotFound() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @WithUserDetails(value = ADMIN_MAIL)
//    void delete() throws Exception {
//        perform(MockMvcRequestBuilders.delete(REST_URL + VOTE_ID_USER))
//                .andDo(print())
//                .andExpect(status().isNoContent());
//        assertFalse(repository.findById(VOTE_ID_USER).isPresent());
//    }
}