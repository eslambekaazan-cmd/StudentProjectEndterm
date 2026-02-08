package service.interfaces;

import java.util.List;

public interface CrudService<T> {

    T create(T entity);

    T getById(int id);

    List<T> getAll();

    T update(T entity);

    void delete(int id);
}
