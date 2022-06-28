package by.intexsoft.study.repositories;

import by.intexsoft.study.daomodel.Feedback;

import java.util.List;

public interface FeedbackDAO extends DAO<Feedback> {

    List<Feedback> findFeedbacksByBookId(Long book_id);
}
