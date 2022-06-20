package by.intexsoft.study.dao.impl;

import by.intexsoft.study.dao.DAO;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public abstract class DAOImpl<T> implements DAO<T> {

    private EntityManager entityManager;

    private Class<T> clazz;

    public DAOImpl(EntityManager entityManager, Class clazz) {
        this.entityManager = entityManager;
        this.clazz = clazz;
    }
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public T findById(Long id) {
        return entityManager.find(clazz, id);
    }

    @Override
    public List<T> findByIds(List<Long> list) {
        List<T> result = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            result.add(findById(list.get(i)));
        }
        return result;
    }

    @Override
    public T createEntity(T t){
        entityManager.persist(t);
        return t;
    }


    @Override
    public T updateEntity(T t) {
        try {
            entityManager.merge(t);
            return t;
        }  catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(Long ident) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<T> criteriaDelete = criteriaBuilder.createCriteriaDelete(clazz);
        Root<T> root = criteriaDelete.from(clazz);
        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), ident));
        Query query = entityManager.createQuery(criteriaDelete);
        query.executeUpdate();
    }

    @Override
    public void deleteAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<T> delete = criteriaBuilder.createCriteriaDelete(clazz);
        delete.from(clazz);
        Query query = entityManager.createQuery(delete);
        query.executeUpdate();
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> rootEntry = criteriaQuery.from(clazz);
        CriteriaQuery<T> all = criteriaQuery.select(rootEntry);
        criteriaQuery.select(rootEntry);
        Query query = entityManager.createQuery(criteriaQuery);
        List<T> result = query.getResultList();
        return  result;
    }


}