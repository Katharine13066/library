package by.intexsoft.study.repositories.impl;

import by.intexsoft.study.repositories.AuthorDAO;
import by.intexsoft.study.daomodel.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class AuthorDAOImpl extends DAOImpl<Author> implements AuthorDAO {

    @Autowired
    public AuthorDAOImpl(EntityManager entityManager) {
        super(entityManager, Author.class);
    }
}
