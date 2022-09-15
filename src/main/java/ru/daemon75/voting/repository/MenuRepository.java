package ru.daemon75.voting.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.daemon75.voting.model.Menu;

@Transactional(readOnly = true)
public interface MenuRepository extends BaseRepository<Menu> {

}
