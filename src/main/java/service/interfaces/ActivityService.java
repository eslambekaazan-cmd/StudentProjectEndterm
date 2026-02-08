package service.interfaces;

import model.base.SelfCareActivityBase;

import java.util.List;

public interface ActivityService {
    SelfCareActivityBase create(SelfCareActivityBase activity);
    SelfCareActivityBase getById(int id);
    List<SelfCareActivityBase> getAll();
    List<SelfCareActivityBase> getAllSortedByScoreDesc();
    SelfCareActivityBase update(SelfCareActivityBase activity);
    void delete(int id);
}
