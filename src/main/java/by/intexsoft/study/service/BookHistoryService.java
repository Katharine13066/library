package by.intexsoft.study.service;

import by.intexsoft.study.model.AuthorDto;
import by.intexsoft.study.model.BookDto;
import by.intexsoft.study.model.BookHistoryDto;

import java.util.List;

public interface BookHistoryService extends LibraryService<BookHistoryDto> {

    BookHistoryDto findBookHistoryByBookAndUserIds(Long bookId, Long userId);
    List<BookHistoryDto> findBookHistoryByBookId(Long bookId);
    List<BookDto> get10TheMostPopularBooks();
    List<AuthorDto> get10TheMostPopularAuthors();
}
