package ru.javaops.topjava.web.menu;

import ru.javaops.topjava.model.Menu;
import ru.javaops.topjava.web.MatcherFactory;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javaops.topjava.web.dish.DishTestData.*;

public class MenuTestData {
    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Menu.class, "dishes");
    public static MatcherFactory.Matcher<Menu> MENU_WITH_DISHES_MATCHER =
            MatcherFactory.usingAssertions(Menu.class,
                    //     No need use ignoringAllOverriddenEquals, see https://assertj.github.io/doc/#breaking-changes
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("dishes.menu", "restaurant").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final int TODAY_ID = 1;
    public static final LocalDate TODAY_DATE = LocalDate.now();
    public static final int TOMORROW_ID = 2;
    public static final LocalDate TOMORROW_DATE = LocalDate.now().plusDays(1);
    public static final LocalDate NEW_DATE = LocalDate.now().plusDays(2);
    public static final LocalDate UPDATED_DATE = LocalDate.now().minusDays(1);
    public static final Menu today = new Menu(TODAY_ID, TODAY_DATE);
    public static final Menu tomorrow = new Menu(TOMORROW_ID, TOMORROW_DATE);

    static {
        today.setDishes(List.of(dish1, dish2, dish3, dish4, dish5, dish6, dish7));
        tomorrow.setDishes(List.of(dish8, dish9, dish10));
    }

    public static Menu getNew() {
        return new Menu(null, NEW_DATE);
    }

    public static Menu getUpdated() {
        return new Menu(TOMORROW_ID, UPDATED_DATE);
    }
}
