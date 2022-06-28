package by.intexsoft.study.service;

import by.intexsoft.study.model.BookHistoryDTO;
public interface BookHistoryService extends LibraryService<BookHistoryDTO> {

    BookHistoryDTO findBookHistoryByBookAndUserIds(Long book_id, Long user_id);
}
