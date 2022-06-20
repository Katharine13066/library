package by.intexsoft.study.dao;

import java.util.List;

public interface DAO<T>{

    T createEntity(T t);
    T findById(Long id);
    List<T> findByIds(List<Long> list);
    List<T> findAll();
    T updateEntity(T t);
    void deleteAll();
    void deleteById(Long id);
}
