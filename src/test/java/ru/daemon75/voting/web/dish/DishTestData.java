package ru.daemon75.voting.web.dish;

import ru.daemon75.voting.model.Dish;
import ru.daemon75.voting.web.MatcherFactory;

import java.util.List;

public class DishTestData {

    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.
            usingIgnoringFieldsComparator(Dish.class, "restaurant", "menu");

    public static final int DISH1_ID = 1;
    public static final Dish dish1 = new Dish(DISH1_ID, "Beef Stroganoff", 28);
    public static final Dish dish2 = new Dish(DISH1_ID + 1, "Tourin", 22);
    public static final Dish dish3 = new Dish(DISH1_ID + 2, "Caesar", 15);
    public static final Dish dish4 = new Dish(DISH1_ID + 3, "Espresso", 5);
    public static final Dish dish5 = new Dish(DISH1_ID + 4, "Eggs Benedict", 20);
    public static final Dish dish6 = new Dish(DISH1_ID + 5, "Buttermilk pancake", 15);
    public static final Dish dish7 = new Dish(DISH1_ID + 6, "Smoothie", 7);
    public static final Dish dish8 = new Dish(DISH1_ID + 7, "Chicken schnitzel", 22);
    public static final Dish dish9 = new Dish(DISH1_ID + 8, "Prague salad", 14);
    public static final Dish dish10 = new Dish(DISH1_ID + 9, "San Pellegrino 750ml", 8);
    public static final List<Dish> dishes = List.of(dish1, dish2, dish3, dish4, dish5, dish6, dish7, dish8, dish9, dish10);

    public static Dish getNew() {
        return new Dish(null, "New Dish", 9);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, "Updated Dish", 30);
    }
}
