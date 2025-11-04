package src.repository.interfaces;

import java.util.List;
import src.exception.NotFoundExecption;
import src.exception.AlreadyExistsException;

public interface RepoInterface<T> {
    void loadAll();
    List<T> getAll();
    void add(T obj);
    void update(T obj);
    void deleteById(int id);
    T getById(int id) throws NotFoundExecption;
    void syncChanges();
}
