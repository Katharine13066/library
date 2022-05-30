package by.intexsoft.study.storage.impl;

import by.intexsoft.study.fileUtils.JSONBookReader;
import by.intexsoft.study.fileUtils.JSONBookWriter;
import by.intexsoft.study.filters.Filter;
import by.intexsoft.study.filters.OperatorManager;
import by.intexsoft.study.model.Book;
import by.intexsoft.study.orders.Order;
import by.intexsoft.study.orders.OrderManager;
import by.intexsoft.study.storage.AbstractBookStorageWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Component
public class JSONBookStorageWorkerImpl extends AbstractBookStorageWorker {

    private JSONBookReader jsonBookReader;
    private JSONBookWriter jsonBookWriter;

    @Autowired
    public JSONBookStorageWorkerImpl(OperatorManager operatorManager, OrderManager orderManager, JSONBookReader jsonBookReader, JSONBookWriter jsonBookWriter) {
        super(operatorManager, orderManager);
        this.jsonBookReader = jsonBookReader;
        this.jsonBookWriter = jsonBookWriter;
    }

    @Override
    public Book createBook(Book book) throws IOException {
        List<Book> library = jsonBookReader.readJSON();
        long num = System.currentTimeMillis();
        String id = String.valueOf(num);
        book.setBookID(id);
        library.add(book);
        jsonBookWriter.writeJSON(library);
        return book;
    }

    @Override
    public Book updateBook(Book book) throws IOException {
        List<Book> library = jsonBookReader.readJSON();
        updateBookById(library, book);
        jsonBookWriter.writeJSON(library);
        return book;
    }

    @Override
    public void deleteBookById(String id) throws IOException {
        List<Book> library = jsonBookReader.readJSON();
        for(int i = 0; i < library.size(); i++){
            if (library.get(i).getBookID().equals(id)){
                library.remove(i);
                break;
            }
        }
        jsonBookWriter.writeJSON(library);
    }

    @Override
    public Book findBookById(String id) throws IOException {
        List<Book> library = jsonBookReader.readJSON();
        Book book = new Book();
        for (Book bookIterator: library){
            if (bookIterator.getBookID().equals(id)){
                book.setBookID(bookIterator.getBookID());
                book.setBookName(bookIterator.getBookName());
                book.setAuthorID(bookIterator.getAuthorID());
                book.setPublisher(bookIterator.getPublisher());
                book.setPublicationDate(bookIterator.getPublicationDate());
            }
        }
        return book;
    }

    @Override
    public Book findBookByAuthorId(String id) throws IOException {
        List<Book> library = jsonBookReader.readJSON();
        Book book = new Book();
        for (Book bookIterator: library){
            if (bookIterator.getAuthorID().equals(id)){
                book.setBookID(bookIterator.getBookID());
                book.setBookName(bookIterator.getBookName());
                book.setAuthorID(bookIterator.getAuthorID());
                book.setPublisher(bookIterator.getPublisher());
                book.setPublicationDate(bookIterator.getPublicationDate());
            }
        }
        return book;
    }

    @Override
    public List<Book> getAllBooks() throws IOException {
        return jsonBookReader.readJSON();
    }

    @Override
    public List<Book> getAllBooks(List<Filter> filters) throws IOException, NoSuchMethodException {
        List<Book> library = jsonBookReader.readJSON();
        filterBook(filters, library);
        return getBookResult(library);
    }

    @Override
    public List<Book> getAllBooks(List<Filter> filters, List<Order> orders) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Book> library = jsonBookReader.readJSON();
        filterBook(filters, library);
        orderBook(orders, library);
        return getBookResult(library);
    }

    @Override
    public List<Book> orderAllBooks(List<Order> orders) throws NoSuchMethodException, IOException, InvocationTargetException, IllegalAccessException {
        List<Book> library = jsonBookReader.readJSON();
        orderBook(orders, library);
        return getBookResult(library);
    }

}
