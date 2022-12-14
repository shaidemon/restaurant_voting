package ru.daemon75.voting.web.menu;

import ru.daemon75.voting.model.Menu;
import ru.daemon75.voting.model.MenuItem;
import ru.daemon75.voting.web.MatcherFactory;

import java.time.LocalDate;
import java.util.List;

import static ru.daemon75.voting.util.Util.TODAY;
import static ru.daemon75.voting.util.Util.YESTERDAY;
import static ru.daemon75.voting.web.menuItem.MenuItemTestData.MENU_ITEMS_YESTERDAY;
import static ru.daemon75.voting.web.restaurant.RestaurantTestData.*;

public class MenuTestData {
    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Menu.class,
            "restaurant.menuItems", "restaurant.votes", "restaurant.menus");

    public static final int YESTERDAY_ID = 1;
    public static final int TODAY_ID = 2;

    public static final LocalDate UPDATED_DATE = LocalDate.now().minusDays(1);
    public static final Menu menuYesterday = new Menu(YESTERDAY_ID, YESTERDAY, astoria);
    public static final Menu menuToday1 = new Menu(TODAY_ID, TODAY, astoria);
    public static final Menu menuToday2 = new Menu(TODAY_ID + 1, TODAY, seasons);
    public static final List<MenuItem> yesterdayItems = MENU_ITEMS_YESTERDAY;

    static {
    }

    public static Menu getNew() {
        return new Menu(null, TODAY, prague);
    }

    public static Menu getUpdated() {
        return new Menu(TODAY_ID, TODAY, prague);
    }
}
