package by.intexsoft.study.dao.impl;

import by.intexsoft.study.dao.AuthorDAO;
import by.intexsoft.study.daomodel.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class AuthorDAOImpl extends DAOImpl<Author> implements AuthorDAO {

    @Autowired
    public AuthorDAOImpl(EntityManager entityManager) {
        super(entityManager, Author.class);
    }
}
