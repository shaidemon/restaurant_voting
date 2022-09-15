package ru.daemon75.voting.web.restaurant;

import ru.daemon75.voting.model.Restaurant;
import ru.daemon75.voting.web.MatcherFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.daemon75.voting.web.menuItem.MenuItemTestData.*;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menuItems", "menus");
    public static MatcherFactory.Matcher<Restaurant> RESTAURANT_WITH_ITEMS_MATCHER =
            MatcherFactory.usingAssertions(Restaurant.class,
                    //     No need use ignoringAllOverriddenEquals, see https://assertj.github.io/doc/#breaking-changes
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("MENU_ITEMS.restaurant").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final int ASTORIA_ID = 1;
    public static final int SEASONS_ID = 2;
    public static final int PRAGUE_ID = 3;
    public static final int NOT_FOUND = 100;

    public static final Restaurant astoria = new Restaurant(ASTORIA_ID, "Astoria");
    public static final Restaurant seasons = new Restaurant(SEASONS_ID, "Four seasons");
    public static final Restaurant prague = new Restaurant(PRAGUE_ID, "Prague");

    static {
        astoria.setMenuItems(List.of(MENU_ITEM_1, MENU_ITEM_2, MENU_ITEM_3, MENU_ITEM_4, MENU_ITEM_5, MENU_ITEM_6));
        seasons.setMenuItems(List.of(MENU_ITEM_7, MENU_ITEM_8, MENU_ITEM_9));
        prague.setMenuItems(List.of(MENU_ITEM_10, MENU_ITEM_11, MENU_ITEM_12));
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "New Restaurant");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(SEASONS_ID, "updated name");
    }
}
