package by.intexsoft.study.repositories.impl;

import by.intexsoft.study.repositories.AuthorDao;
import by.intexsoft.study.daomodel.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class AuthorDaoImpl extends DaoImpl<Author> implements AuthorDao {

    @Autowired
    public AuthorDaoImpl(EntityManager entityManager) {
        super(entityManager, Author.class);
    }
}
