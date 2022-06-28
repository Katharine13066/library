package by.intexsoft.study.repositories;

import by.intexsoft.study.daomodel.BookHistory;
import by.intexsoft.study.model.BookHistoryDTO;

import java.util.List;

public interface BookHistoryDAO  extends DAO<BookHistory>{
    List<BookHistoryDTO> findBookHistoryByBookAndUserIds(Long book_id, Long user_id);
}
