package ru.daemon75.voting.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.daemon75.voting.model.Dish;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish>{

    
}
