package ru.javaops.topjava.web.menu;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava.model.Menu;
import ru.javaops.topjava.repository.MenuRepository;

import java.util.List;

@RestController
@RequestMapping(value = MenuCommonController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class MenuCommonController {
    static final String REST_URL = "/api/menus";
    private final MenuRepository repository;

    @GetMapping()
    public List<Menu> getAll() {
        log.info("getAll");
        return repository.findAll(Sort.by(Sort.Order.desc("date_menu")));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> get(@PathVariable int id) {
        log.info("get {}", id);
        return ResponseEntity.of(repository.findById(id));
    }

    @GetMapping("/{id}/with-dishes")
    public ResponseEntity<Menu> getWithDishes(@PathVariable int id) {
        log.info("getWithDishes {}", id);
        return ResponseEntity.of(repository.getWithDishes(id));
    }

}
