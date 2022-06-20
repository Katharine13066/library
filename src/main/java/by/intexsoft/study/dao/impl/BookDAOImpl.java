package by.intexsoft.study.dao.impl;

import by.intexsoft.study.dao.BookDAO;
import by.intexsoft.study.daomodel.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class BookDAOImpl extends DAOImpl<Book> implements BookDAO {

    @Autowired
    public BookDAOImpl(EntityManager entityManager) {
        super(entityManager, Book.class);
    }

}
