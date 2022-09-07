package ru.javaops.topjava.web.menu;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.topjava.model.Menu;
import ru.javaops.topjava.repository.MenuRepository;

import java.net.URI;
import java.util.List;

import static ru.javaops.topjava.util.validation.ValidationUtil.assureIdConsistent;
import static ru.javaops.topjava.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = MenuAdminController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class MenuAdminController {
    static final String REST_URL = "/api/admin/menus";
    private final MenuRepository repository;

//    @GetMapping()
//    public List<Menu> getAll() {
//        log.info("getAll");
//        return repository.findAll(Sort.by(Sort.Order.desc("date_menu")));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Menu> get(@PathVariable int id) {
//        log.info("get {}", id);
//        return ResponseEntity.of(repository.findById(id));
//    }
//
//    @GetMapping("/{id}/with-dishes")
//    public ResponseEntity<Menu> getWithDishes(@PathVariable int id) {
//        log.info("getWithDishes {}", id);
//        return ResponseEntity.of(repository.getWithDishes(id));
//    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@RequestBody Menu menu) {
        log.info("create {}", menu);
        checkNew(menu);
        repository.save(menu);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(menu.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(menu);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Menu menu, @PathVariable int id) {
        log.info("update {} with id={}", menu, id);
        assureIdConsistent(menu, id);
        repository.save(menu);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }
}