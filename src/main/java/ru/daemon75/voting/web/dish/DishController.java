package ru.daemon75.voting.web.dish;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.daemon75.voting.repository.DishRepository;
import ru.daemon75.voting.util.validation.ValidationUtil;
import ru.daemon75.voting.model.Dish;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@CacheConfig(cacheNames = "dish")
public class DishController {

    static final String REST_URL = "/api/admin/dishes";
    private final DishRepository repository;

    @GetMapping
    @Cacheable
    public List<Dish> getAll() {
        log.info("getAll");
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Dish> get(@PathVariable int id) {
        log.info("get id={}", id);
        return ResponseEntity.of(repository.findById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(allEntries = true)
    public ResponseEntity<Dish> create(@RequestBody @Valid Dish dish) {
        log.info("create {}", dish);
        ValidationUtil.checkNew(dish);
        Dish created = repository.save(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true)
    public void update(@RequestBody Dish dish, @PathVariable int id) {
        log.info("update {} with id={}", dish, id);
        ValidationUtil.assureIdConsistent(dish, id);
        repository.save(dish);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true)
    public void delete(@PathVariable int id) {
        log.info("delete id={}", id);
        repository.delete(id);
    }
}