package by.intexsoft.study.repositories.impl;

import by.intexsoft.study.daomodel.BookHistory;
import by.intexsoft.study.model.BookHistoryDTO;
import by.intexsoft.study.repositories.BookHistoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class BookHistoryDAOImpl extends DAOImpl<BookHistory> implements BookHistoryDAO {

    @Autowired
    public BookHistoryDAOImpl(EntityManager entityManager) {
        super(entityManager, BookHistory.class);
    }


    @Override
    public List<BookHistoryDTO> findBookHistoryByBookAndUserIds(Long book_id, Long user_id) {
        Query query = getEntityManager().createQuery("FROM BookHistory where book_id =:book_id and user_id =:user_id");
        query.setParameter("book_id", book_id);
        query.setParameter("user_id", user_id);
        List<BookHistoryDTO> list = query.getResultList();
        return list;
    }
}

