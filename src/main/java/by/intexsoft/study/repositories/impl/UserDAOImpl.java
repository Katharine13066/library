package by.intexsoft.study.repositories.impl;

import by.intexsoft.study.daomodel.User;
import by.intexsoft.study.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserDAOImpl extends DAOImpl<User> implements UserDAO {

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        super(entityManager, User.class);
    }
}
