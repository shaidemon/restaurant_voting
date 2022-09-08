package ru.daemon75.voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.daemon75.voting.repository.MealRepository;
import ru.daemon75.voting.model.Meal;
import ru.daemon75.voting.repository.UserRepository;

@Service
@AllArgsConstructor
public class MealService {
    private final MealRepository mealRepository;
    private final UserRepository userRepository;

    @Transactional
    public Meal save(Meal meal, int userId) {
        meal.setUser(userRepository.getExisted(userId));
        return mealRepository.save(meal);
    }
}
