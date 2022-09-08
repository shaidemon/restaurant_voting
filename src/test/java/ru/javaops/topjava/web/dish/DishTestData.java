package ru.javaops.topjava.web.dish;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javaops.topjava.model.Dish;
import ru.javaops.topjava.repository.DishRepository;
import ru.javaops.topjava.repository.RestaurantRepository;
import ru.javaops.topjava.web.MatcherFactory;

import java.util.List;

public class DishTestData {

    public static final MatcherFactory.Matcher<Dish> MEAL_MATCHER = MatcherFactory.
            usingIgnoringFieldsComparator(Dish.class, "restaurant", "menu");
    public static final int DISH1_ID = 1;
    public static final Dish dish1 = new Dish(DISH1_ID, "Beef Stroganoff", 28 );
    public static final Dish dish2 = new Dish(DISH1_ID+1, "Tourin", 22 );
    public static final Dish dish3 = new Dish(DISH1_ID+2, "Caesar", 15 );
    public static final Dish dish4 = new Dish(DISH1_ID+3, "Espresso", 5 );
    public static final Dish dish5 = new Dish(DISH1_ID+4, "Eggs Benedict", 20 );
    public static final Dish dish6 = new Dish(DISH1_ID+5, "Buttermilk pancake", 15 );
    public static final Dish dish7 = new Dish(DISH1_ID+6, "Smoothie", 7 );
    public static final Dish dish8 = new Dish(DISH1_ID+7, "Chicken schnitzel", 22 );
    public static final Dish dish9 = new Dish(DISH1_ID+8, "Prague salad", 14 );
    public static final Dish dish10 = new Dish(DISH1_ID+9, "San Pellegrino 750ml", 8 );
    public static final List<Dish> dishes = List.of(dish1, dish2, dish3, dish4, dish5, dish6, dish7, dish8, dish9, dish10);
}
