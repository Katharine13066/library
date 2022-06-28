package by.intexsoft.study.repositories.impl;

import by.intexsoft.study.daomodel.BookHistory;
import by.intexsoft.study.repositories.BookHistoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class BookHistoryDAOImpl extends DAOImpl<BookHistory> implements BookHistoryDAO {

    @Autowired
    public BookHistoryDAOImpl(EntityManager entityManager) {
        super(entityManager, BookHistory.class);
    }


    @Override
    public List<BookHistory> findBookHistoryByBookAndUserIds(Long book_id, Long user_id) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<BookHistory> criteriaQuery = criteriaBuilder.createQuery(BookHistory.class);
        Root<BookHistory> from = criteriaQuery.from(BookHistory.class);
        criteriaQuery.select(from).where(criteriaBuilder.and(criteriaBuilder.equal(from.get("bookID"), book_id), criteriaBuilder.equal(from.get("userID"), user_id)));
        TypedQuery<BookHistory> typedQuery = getEntityManager().createQuery(criteriaQuery);
        List<BookHistory> result = typedQuery.getResultList();
        return result;
    }

    @Override
    public List<BookHistory> findBookHistoryByBookId(Long book_id) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<BookHistory> query = criteriaBuilder.createQuery(BookHistory.class);
        Root<BookHistory> from = query.from(BookHistory.class);
        query.select(from).where(criteriaBuilder.equal(from.get("bookID"), book_id));
        TypedQuery<BookHistory> typedQuery = getEntityManager().createQuery(query);
        List<BookHistory> result = typedQuery.getResultList();
        return result;
    }
}

