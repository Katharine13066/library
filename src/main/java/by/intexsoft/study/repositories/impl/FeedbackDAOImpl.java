package by.intexsoft.study.repositories.impl;

import by.intexsoft.study.daomodel.Feedback;
import by.intexsoft.study.repositories.FeedbackDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class FeedbackDAOImpl extends DAOImpl<Feedback> implements FeedbackDAO {

    @Autowired
    public FeedbackDAOImpl(EntityManager entityManager) {
        super(entityManager, Feedback.class);
    }


    @Override
    public List<Feedback> findFeedbacksByBookId(Long book_id) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Feedback> query = criteriaBuilder.createQuery(Feedback.class);
        Root<Feedback> from = query.from(Feedback.class);
        query.select(from).where(criteriaBuilder.equal(from.get("bookID"), book_id));
        TypedQuery<Feedback> typedQuery = getEntityManager().createQuery(query);
        List<Feedback> result = typedQuery.getResultList();
        return result;
    }
}
