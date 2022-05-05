package by.intexsoft.study.storage;

import by.intexsoft.study.model.Book;

import java.io.IOException;
import java.util.List;

public interface StorageWorker {
    Book createBook(Book book) throws IOException;
    Book updateBook(Book book) throws IOException;
    void deleteBookById(String id) throws IOException;
    List<Book> getAllBooks();
}
