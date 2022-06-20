package by.intexsoft.study.repositories.impl;

import by.intexsoft.study.repositories.BookDAO;
import by.intexsoft.study.daomodel.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class BookDAOImpl extends DAOImpl<Book> implements BookDAO {

    @Autowired
    public BookDAOImpl(EntityManager entityManager) {
        super(entityManager, Book.class);
    }

}
