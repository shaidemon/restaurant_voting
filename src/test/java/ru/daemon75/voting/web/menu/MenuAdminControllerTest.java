package ru.daemon75.voting.web.menu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.daemon75.voting.model.Menu;
import ru.daemon75.voting.repository.MenuRepository;
import ru.daemon75.voting.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.daemon75.voting.util.JsonUtil.writeValue;
import static ru.daemon75.voting.web.user.UserTestData.ADMIN_MAIL;

class MenuAdminControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MenuAdminController.REST_URL + '/';

    @Autowired
    private MenuRepository menuRepository;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithLocation() throws Exception {
        Menu newMenu = MenuTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(newMenu)))
                .andExpect(status().isCreated());
        Menu created = MenuTestData.MENU_MATCHER.readFromJson(action);
        int newId = created.id();
        newMenu.setId(newId);
        MenuTestData.MENU_MATCHER.assertMatch(created, newMenu);
        MenuTestData.MENU_MATCHER.assertMatch(menuRepository.getExisted(newId), newMenu);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        Menu updated = MenuTestData.getUpdated();
        updated.setId(null);
        perform(MockMvcRequestBuilders.put(REST_URL+ MenuTestData.TOMORROW_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());
        MenuTestData.MENU_MATCHER.assertMatch(menuRepository.getExisted(MenuTestData.TOMORROW_ID), MenuTestData.getUpdated());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL+ MenuTestData.TOMORROW_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(menuRepository.findById(MenuTestData.TOMORROW_ID).isPresent());
    }
}