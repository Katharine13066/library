package by.intexsoft.study.repositories;

import by.intexsoft.study.daomodel.Author;
import by.intexsoft.study.daomodel.Book;
import by.intexsoft.study.daomodel.BookHistory;

import java.util.List;

public interface BookHistoryDao extends Dao<BookHistory> {
    List<BookHistory> findBookHistoryByBookAndUserIds(Long bookId, Long userId);
    List<BookHistory> findBookHistoryByBookId(Long bookId);
    List<Book> get10TheMostPopularBooks();
    List<Author> get10TheMostPopularAuthors();
}
