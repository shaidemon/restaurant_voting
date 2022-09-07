package ru.javaops.topjava.repository;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository{

    
}
