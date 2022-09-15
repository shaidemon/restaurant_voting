package ru.daemon75.voting.web.menuItem;

import ru.daemon75.voting.model.MenuItem;
import ru.daemon75.voting.web.MatcherFactory;

import java.util.List;

import static ru.daemon75.voting.util.Util.TODAY;
import static ru.daemon75.voting.util.Util.YESTERDAY;

public class MenuItemTestData {

    public static final MatcherFactory.Matcher<MenuItem> MENU_ITEM_MATCHER = MatcherFactory.
            usingIgnoringFieldsComparator(MenuItem.class, "restaurant");

    public static final int MENU_ITEM_1_ID = 1;

    public static final MenuItem MENU_ITEM_1 = new MenuItem(MENU_ITEM_1_ID, "Beef Stroganoff", 28, YESTERDAY);
    public static final MenuItem MENU_ITEM_2 = new MenuItem(MENU_ITEM_1_ID + 1, "Caesar", 15, YESTERDAY);
    public static final MenuItem MENU_ITEM_3 = new MenuItem(MENU_ITEM_1_ID + 2, "Espresso", 5, YESTERDAY);
    public static final MenuItem MENU_ITEM_4 = new MenuItem(MENU_ITEM_1_ID + 3, "Tourin", 22, TODAY);
    public static final MenuItem MENU_ITEM_5 = new MenuItem(MENU_ITEM_1_ID + 4, "Caesar", 15, TODAY);
    public static final MenuItem MENU_ITEM_6 = new MenuItem(MENU_ITEM_1_ID + 5, "Espresso", 5, TODAY);
    public static final MenuItem MENU_ITEM_7 = new MenuItem(MENU_ITEM_1_ID + 6, "Eggs Benedict", 20, TODAY);
    public static final MenuItem MENU_ITEM_8 = new MenuItem(MENU_ITEM_1_ID + 7, "Buttermilk pancake", 15, TODAY);
    public static final MenuItem MENU_ITEM_9 = new MenuItem(MENU_ITEM_1_ID + 8, "Smoothie", 7, TODAY);
    public static final MenuItem MENU_ITEM_10 = new MenuItem(MENU_ITEM_1_ID + 9, "Chicken schnitzel", 22, TODAY);
    public static final MenuItem MENU_ITEM_11 = new MenuItem(MENU_ITEM_1_ID + 10, "Prague salad", 14, TODAY);
    public static final MenuItem MENU_ITEM_12 = new MenuItem(MENU_ITEM_1_ID + 11, "San Pellegrino 750ml", 8, TODAY);
    public static final List<MenuItem> MENU_ITEMS_YESTERDAY = List.of(MENU_ITEM_1, MENU_ITEM_2, MENU_ITEM_3);
    public static final List<MenuItem> MENU_ITEMS_TODAY = List.of(MENU_ITEM_4, MENU_ITEM_5, MENU_ITEM_6, MENU_ITEM_7, MENU_ITEM_8, MENU_ITEM_9, MENU_ITEM_10, MENU_ITEM_11, MENU_ITEM_12);
    public static final List<MenuItem> MENU_ITEMS = List.of(MENU_ITEM_1, MENU_ITEM_2, MENU_ITEM_3, MENU_ITEM_4, MENU_ITEM_5, MENU_ITEM_6, MENU_ITEM_7, MENU_ITEM_8, MENU_ITEM_9, MENU_ITEM_10, MENU_ITEM_11, MENU_ITEM_12);

    static {

    }

    public static MenuItem getNew() {
        return new MenuItem(null, "New MenuItem", 9, TODAY);
    }

    public static MenuItem getUpdated() {
        return new MenuItem(MENU_ITEM_1_ID, "Updated MenuItem", 17, YESTERDAY);
    }
}
