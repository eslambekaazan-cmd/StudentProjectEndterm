package service.impl;

import exception.DuplicateResourceException;
import exception.InvalidInputException;
import exception.ResourceNotFoundException;
import interfaces.Validatable;
import model.base.SelfCareActivityBase;
import repository.interfaces.ActivityRepository;
import service.interfaces.ActivityService;

import java.util.List;

public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepo;

    public ActivityServiceImpl(ActivityRepository activityRepo) {
        this.activityRepo = activityRepo;
    }

    @Override
    public SelfCareActivityBase create(SelfCareActivityBase activity) {
        if (activity == null) throw new InvalidInputException("activity is null");
        activity.validate();

        String cleanName = Validatable.notNullOrTrim(activity.getName());
        if (cleanName.isEmpty()) throw new InvalidInputException("name is empty");
        activity.setName(cleanName);

        if (activityRepo.existsByName(activity.getName())) {
            throw new DuplicateResourceException("Activity with name already exists: " + activity.getName());
        }
        return activityRepo.create(activity);
    }

    @Override
    public SelfCareActivityBase getById(int id) {
        if (id <= 0) throw new InvalidInputException("id must be > 0");
        return activityRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found: id=" + id));
    }

    @Override
    public List<SelfCareActivityBase> getAll() {
        return activityRepo.findAll();
    }

    @Override
    public List<SelfCareActivityBase> getAllSortedByScoreDesc() {
        List<SelfCareActivityBase> list = activityRepo.findAll();
        list.sort((a, b) -> Integer.compare(b.estimateScore(), a.estimateScore())); // lambda
        return list;
    }

    @Override
    public SelfCareActivityBase update(SelfCareActivityBase activity) {
        if (activity == null) throw new InvalidInputException("activity is null");
        if (activity.getId() <= 0) throw new InvalidInputException("id must be > 0");
        activity.validate();

        getById(activity.getId()); // ensure exists
        activityRepo.update(activity);
        return getById(activity.getId());
    }

    @Override
    public void delete(int id) {
        getById(id);
        activityRepo.delete(id);
    }
}
