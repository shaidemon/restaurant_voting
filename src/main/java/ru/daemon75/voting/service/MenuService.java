package ru.daemon75.voting.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.daemon75.voting.model.Menu;
import ru.daemon75.voting.model.MenuItem;
import ru.daemon75.voting.repository.MenuItemRepository;
import ru.daemon75.voting.repository.MenuRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class MenuService {
    private final MenuItemRepository menuItemRepository;
    private final MenuRepository menuRepository;

    public List<MenuItem> getItems(int menu_id) {
        Menu menu = menuRepository.getExisted(menu_id);
        LocalDate date = menu.getDate_menu();
        return (menuItemRepository.getItemsMenu(menu.getRestaurant(), date));
    }
}
