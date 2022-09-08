package ru.daemon75.voting.web.restaurant;

import ru.daemon75.voting.web.MatcherFactory;
import ru.daemon75.voting.model.Restaurant;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.daemon75.voting.web.dish.DishTestData.*;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class,  "dishes", "votes");
    public static MatcherFactory.Matcher<Restaurant> RESTAURANT_WITH_DISHES_MATCHER =
            MatcherFactory.usingAssertions(Restaurant.class,
                    //     No need use ignoringAllOverriddenEquals, see https://assertj.github.io/doc/#breaking-changes
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields( "dishes.restaurant", "menu").isEqualTo(e),
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
        astoria.setDishes(List.of(dish1, dish2, dish3, dish4));
        seasons.setDishes(List.of(dish5, dish6, dish7));
        prague.setDishes(List.of(dish8, dish9, dish10));
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "New Restaurant");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(SEASONS_ID, "updated name");
    }
}
