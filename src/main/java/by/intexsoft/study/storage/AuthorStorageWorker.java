package by.intexsoft.study.storage;

import by.intexsoft.study.filters.Filter;
import by.intexsoft.study.model.Author;
import by.intexsoft.study.orders.Order;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface AuthorStorageWorker {
    Author createAuthor(Author author) throws IOException;
    Author updateAuthor(Author author) throws IOException;
    void deleteAuthorById(String id) throws IOException;
    Author findAuthorById(String id) throws IOException;
    List<Author> getAllAuthor() throws IOException;
    List<Author> getAllAuthor(List<Filter> filters) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    List<Author> getAllAuthor(List<Filter> filters, List<Order> orders) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    List<Author> orderAllAuthor(List<Order> orders) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
