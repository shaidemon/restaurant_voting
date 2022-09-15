package ru.daemon75.voting.web.menu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.daemon75.voting.repository.MenuRepository;
import ru.daemon75.voting.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.daemon75.voting.web.menu.MenuTestData.*;
import static ru.daemon75.voting.web.menuItem.MenuItemTestData.*;
import static ru.daemon75.voting.web.user.UserTestData.USER_MAIL;

class MenuCommonControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MenuCommonController.REST_URL + '/';

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getTodayMenus() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(menuToday1, menuToday2));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + TODAY_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(menuToday1));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getItems() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + TODAY_ID + "/items"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_ITEM_MATCHER.contentJson(MENU_ITEM_4, MENU_ITEM_5, MENU_ITEM_6));
    }
}