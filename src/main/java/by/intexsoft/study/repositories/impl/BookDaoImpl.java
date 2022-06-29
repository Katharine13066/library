package by.intexsoft.study.repositories.impl;

import by.intexsoft.study.repositories.BookDao;
import by.intexsoft.study.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class BookDaoImpl extends DaoImpl<Book> implements BookDao {

    @Autowired
    public BookDaoImpl(EntityManager entityManager) {
        super(entityManager, Book.class);
    }

}
