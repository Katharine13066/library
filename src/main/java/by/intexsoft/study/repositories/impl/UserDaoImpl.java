package by.intexsoft.study.repositories.impl;

import by.intexsoft.study.model.User;
import by.intexsoft.study.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;

@Repository
public class UserDaoImpl extends DaoImpl<User> implements UserDao {

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        super(entityManager, User.class);
    }
}
