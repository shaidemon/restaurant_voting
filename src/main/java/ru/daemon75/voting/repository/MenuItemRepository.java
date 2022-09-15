package ru.daemon75.voting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.daemon75.voting.model.MenuItem;
import ru.daemon75.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface MenuItemRepository extends BaseRepository<MenuItem>{

    @Query("SELECT i FROM MenuItem i WHERE i.restaurant=?1 AND i.dateMenu=?2")
    List<MenuItem> getItemsMenu(Restaurant restaurant, LocalDate date);
}
