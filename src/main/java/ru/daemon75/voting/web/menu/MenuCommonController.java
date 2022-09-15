package ru.daemon75.voting.web.menu;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.daemon75.voting.model.Menu;
import ru.daemon75.voting.model.MenuItem;
import ru.daemon75.voting.repository.MenuRepository;
import ru.daemon75.voting.service.MenuService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = MenuCommonController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
//@CacheConfig(cacheNames = "menu")
public class MenuCommonController {
    static final String REST_URL = "/api/menus";
    private final MenuRepository repository;
    private final MenuService service;

    @GetMapping()
    public List<Menu> getAll() {
        log.info("getAll");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> get(@PathVariable int id) {
        log.info("get {}", id);
        return ResponseEntity.of(repository.findById(id));
    }

    @GetMapping("/{id}/items")
//    @Cacheable
    public ResponseEntity<List<MenuItem>> getItems(@PathVariable int id) {
        log.info("getWithDishes {}", id);
        return ResponseEntity.of(Optional.ofNullable(service.getItems(id)));
    }
}