package service.impl;

import exception.InvalidInputException;
import exception.ResourceNotFoundException;
import model.Routine;
import repository.interfaces.RoutineRepository;
import service.interfaces.CrudService;

import java.util.List;

public class RoutineServiceImpl implements CrudService<Routine> {

    private final RoutineRepository repo;

    public RoutineServiceImpl(RoutineRepository repo) {
        this.repo = repo;
    }

    @Override
    public Routine create(Routine entity) {
        if (entity.getName() == null || entity.getName().isBlank()) {
            throw new InvalidInputException("Routine name cannot be empty");
        }
        if (entity.getType() == null) {
            throw new InvalidInputException("RoutineType is required");
        }
        return repo.create(entity);
    }

    @Override
    public Routine getById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Routine not found: " + id));
    }

    @Override
    public List<Routine> getAll() {
        return repo.findAll();
    }

    @Override
    public Routine update(Routine entity) {
        if (entity.getId() <= 0) {
            throw new InvalidInputException("Invalid Routine id");
        }
        return repo.update(entity);
    }

    @Override
    public void delete(int id) {
        repo.delete(id);
    }
}
