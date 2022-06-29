package by.intexsoft.study.service;

import by.intexsoft.study.model.BookDto;

public interface BookService extends LibraryService<BookDto>{
    void takeBook(Long bookId, Long userId);
    void returnBook(Long bookId, Long userId);
}
