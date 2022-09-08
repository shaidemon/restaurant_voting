package ru.daemon75.voting.web.meal;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.daemon75.voting.model.Meal;
import ru.daemon75.voting.repository.MealRepository;
import ru.daemon75.voting.service.MealService;
import ru.daemon75.voting.util.DateTimeUtil;
import ru.daemon75.voting.util.MealsUtil;
import ru.daemon75.voting.util.validation.ValidationUtil;
import ru.daemon75.voting.to.MealTo;
import ru.daemon75.voting.web.AuthUser;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = MealController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class MealController {
    static final String REST_URL = "/api/profile/meals";

    private final MealRepository repository;
    private final MealService service;

    @GetMapping("/{id}")
    public ResponseEntity<Meal> get(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        log.info("get meal {} for user {}", id, authUser.id());
        return ResponseEntity.of(repository.get(id, authUser.id()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        log.info("delete {} for user {}", id, authUser.id());
        Meal meal = repository.checkBelong(id, authUser.id());
        repository.delete(meal);
    }

    @GetMapping
    public List<MealTo> getAll(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getAll for user {}", authUser.id());
        return MealsUtil.getTos(repository.getAll(authUser.id()), authUser.getUser().getCaloriesPerDay());
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody Meal meal, @PathVariable int id) {
        int userId = authUser.id();
        log.info("update {} for user {}", meal, userId);
        ValidationUtil.assureIdConsistent(meal, id);
        repository.checkBelong(id, userId);
        service.save(meal, userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody Meal meal) {
        int userId = authUser.id();
        log.info("create {} for user {}", meal, userId);
        ValidationUtil.checkNew(meal);
        Meal created = service.save(meal, userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }


    @GetMapping("/filter")
    public List<MealTo> getBetween(@AuthenticationPrincipal AuthUser authUser,
                                   @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                   @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
                                   @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                   @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime) {

        int userId = authUser.id();
        log.info("getBetween dates({} - {}) time({} - {}) for user {}", startDate, endDate, startTime, endTime, userId);
        List<Meal> mealsDateFiltered = repository.getBetweenHalfOpen(DateTimeUtil.atStartOfDayOrMin(startDate), DateTimeUtil.atStartOfNextDayOrMax(endDate), userId);
        return MealsUtil.getFilteredTos(mealsDateFiltered, authUser.getUser().getCaloriesPerDay(), startTime, endTime);
    }
}