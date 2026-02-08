package repository.interfaces;

import model.base.SelfCareActivityBase;

public interface ActivityRepository extends CrudRepository<SelfCareActivityBase> {
    boolean existsByName(String name);
}
