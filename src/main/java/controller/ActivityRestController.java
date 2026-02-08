package controller;

import dto.ActivityCreateRequest;
import dto.ActivityUpdateRequest;
import model.Routine;
import model.base.SelfCareActivityBase;
import org.springframework.web.bind.annotation.*;
import patterns.ActivityFactory;
import service.interfaces.ActivityService;
import service.interfaces.CrudService;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityRestController {

    private final ActivityService activityService;
    private final CrudService<Routine> routineService;
    private final ActivityFactory factory;

    public ActivityRestController(ActivityService activityService,
                                  CrudService<Routine> routineService,
                                  ActivityFactory factory) {
        this.activityService = activityService;
        this.routineService = routineService;
        this.factory = factory;
    }

    @GetMapping
    public List<SelfCareActivityBase> getAll() {
        return activityService.getAll();
    }

    @GetMapping("/{id}")
    public SelfCareActivityBase getById(@PathVariable int id) {
        return activityService.getById(id);
    }

    @PostMapping
    public SelfCareActivityBase create(@RequestBody ActivityCreateRequest req) {
        Routine routine = routineService.getById(req.routineId);

        SelfCareActivityBase activity = factory.create(
                req.activityType,
                0,
                req.name,
                routine,
                req.minutes,
                req.intensity,
                req.difficulty,
                req.focusArea
        );

        return activityService.create(activity);
    }

    @PutMapping("/{id}")
    public SelfCareActivityBase update(@PathVariable int id, @RequestBody ActivityUpdateRequest req) {
        Routine routine = routineService.getById(req.routineId);

        SelfCareActivityBase activity = factory.create(
                req.activityType,
                id,
                req.name,
                routine,
                req.minutes,
                req.intensity,
                req.difficulty,
                req.focusArea
        );

        return activityService.update(activity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        activityService.delete(id);
    }
}
