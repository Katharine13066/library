package by.intexsoft.study.repositories.impl;

import by.intexsoft.study.daomodel.Feedback;
import by.intexsoft.study.repositories.FeedbackDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class FeedbackDAOImpl extends DAOImpl<Feedback> implements FeedbackDAO {

    @Autowired
    public FeedbackDAOImpl(EntityManager entityManager) {
        super(entityManager, Feedback.class);
    }
}
