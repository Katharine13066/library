package by.intexsoft.study.service;

import by.intexsoft.study.model.BookDTO;

public interface BookService extends LibraryService<BookDTO>{
    void takeBook(Long id);
    void returnBook(Long id);
}
