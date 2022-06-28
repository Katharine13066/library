package by.intexsoft.study.service;

import by.intexsoft.study.model.BookHistoryDTO;

import java.util.List;

public interface BookHistoryService extends LibraryService<BookHistoryDTO> {

    BookHistoryDTO findBookHistoryByBookAndUserIds(Long book_id, Long user_id);
    List<BookHistoryDTO> findBookHistoryByBookId(Long book_id);
}
