package by.intexsoft.study.repositories.impl;

import by.intexsoft.study.daomodel.Feedback;
import by.intexsoft.study.repositories.FeedbackDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class FeedbackDaoImpl extends DaoImpl<Feedback> implements FeedbackDao {

    @Autowired
    public FeedbackDaoImpl(EntityManager entityManager) {
        super(entityManager, Feedback.class);
    }

    @Override
    public List<Feedback> findFeedbacksByBookId(Long bookId) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Feedback> criteriaQuery = criteriaBuilder.createQuery(Feedback.class);
        Root<Feedback> root = criteriaQuery.from(Feedback.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("bookId"), bookId));
        TypedQuery<Feedback> typedQuery = getEntityManager().createQuery(criteriaQuery);
        List<Feedback> result = typedQuery.getResultList();
        return result;
    }
}
