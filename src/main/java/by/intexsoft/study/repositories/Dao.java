package by.intexsoft.study.repositories;

import java.util.List;

public interface Dao<T>{

    T createEntity(T t);
    T findById(Long id);
    List<T> findByIds(List<Long> list);
    List<T> findAll();
    T updateEntity(T t);
    void deleteAll();
    void deleteById(Long id);
}
