package by.intexsoft.study.repositories.impl;

import by.intexsoft.study.daomodel.Author;
import by.intexsoft.study.daomodel.Book;
import by.intexsoft.study.daomodel.BookHistory;
import by.intexsoft.study.repositories.BookHistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class BookHistoryDaoImpl extends DaoImpl<BookHistory> implements BookHistoryDao {

    @Autowired
    public BookHistoryDaoImpl(EntityManager entityManager) {
        super(entityManager, BookHistory.class);
    }

    @Override
    public List<BookHistory> findBookHistoryByBookAndUserIds(Long bookId, Long userId) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<BookHistory> criteriaQuery = criteriaBuilder.createQuery(BookHistory.class);
        Root<BookHistory> root = criteriaQuery.from(BookHistory.class);
        criteriaQuery.select(root).where(criteriaBuilder.and(criteriaBuilder.equal(root.get("bookId"), bookId), criteriaBuilder.equal(root.get("userId"), userId)));
        TypedQuery<BookHistory> typedQuery = getEntityManager().createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public List<BookHistory> findBookHistoryByBookId(Long bookId) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<BookHistory> criteriaQuery = criteriaBuilder.createQuery(BookHistory.class);
        Root<BookHistory> root = criteriaQuery.from(BookHistory.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("bookId"), bookId));
        TypedQuery<BookHistory> typedQuery = getEntityManager().createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public List<Book> get10TheMostPopularBooks() {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<BookHistory> root = criteriaQuery.from(BookHistory.class);
        Join<BookHistory, Book> joinOnBooks =  root.join("books");
        Expression<Long> count = criteriaBuilder.count(root.get("bookId"));
        Expression<Long> countId = criteriaBuilder.count(root.get("id"));
        criteriaQuery.select(joinOnBooks)
                     .groupBy(joinOnBooks.get("bookName"), joinOnBooks.get("id"))
                     .having(criteriaBuilder.gt(count, 0))
                     .orderBy(criteriaBuilder.desc(countId));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Author> get10TheMostPopularAuthors() {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
        Root<BookHistory> root = criteriaQuery.from(BookHistory.class);
        Join<BookHistory, Book> joinOnBooks =  root.join("books");
        Join<Book, Author> joinOnAuthors =  joinOnBooks.join("authors");
        Expression<Long> count = criteriaBuilder.count(root.get("bookId"));
        Expression<Long> countId = criteriaBuilder.count(root.get("id"));
        criteriaQuery.select(joinOnAuthors)
                     .groupBy(joinOnAuthors.get("authorName"), joinOnAuthors.get("id"))
                     .having(criteriaBuilder.gt(count, 0))
                     .orderBy(criteriaBuilder.desc(countId));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

}

