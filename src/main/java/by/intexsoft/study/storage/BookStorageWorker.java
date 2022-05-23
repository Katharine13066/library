package by.intexsoft.study.storage;

import by.intexsoft.study.filters.Filter;
import by.intexsoft.study.model.Book;
import by.intexsoft.study.orders.Order;

import java.io.IOException;
import java.util.List;

public interface BookStorageWorker {
    Book createBook(Book book) throws IOException;
    Book updateBook(Book book) throws IOException;
    void deleteBookById(String id) throws IOException;
    Book findBookById(String id) throws IOException;
    Book findBookByAuthorId(String id) throws IOException;
    List<Book> getAllBooks() throws IOException;
    List<Book> getAllBooks(List<Filter> filters) throws IOException, NoSuchMethodException;
    List<Book> getAllBooks(List<Filter> filters, List<Order> orders) throws IOException, NoSuchMethodException;
    List<Book> orderAllBooks(List<Order> orders) throws NoSuchMethodException, IOException;

}
