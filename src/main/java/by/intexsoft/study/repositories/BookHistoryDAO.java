package by.intexsoft.study.repositories;

import by.intexsoft.study.daomodel.BookHistory;

import java.util.List;

public interface BookHistoryDAO  extends DAO<BookHistory>{
    List<BookHistory> findBookHistoryByBookAndUserIds(Long book_id, Long user_id);
    List<BookHistory> findBookHistoryByBookId(Long book_id);
}
