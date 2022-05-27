package by.intexsoft.study.storage.impl;

import by.intexsoft.study.fileUtils.CSVReader;
import by.intexsoft.study.fileUtils.CSVWriter;
import by.intexsoft.study.filters.Filter;
import by.intexsoft.study.filters.IOperatorHelper;
import by.intexsoft.study.filters.OperatorHandler;
import by.intexsoft.study.filters.OperatorManager;
import by.intexsoft.study.model.Book;
import by.intexsoft.study.orders.IOrderTypesHelper;
import by.intexsoft.study.orders.Order;
import by.intexsoft.study.orders.OrderManager;
import by.intexsoft.study.orders.OrderTypes;
import by.intexsoft.study.parser.BookParser;
import by.intexsoft.study.storage.AbstractBookStorageWorker;
import by.intexsoft.study.storage.BookStorageWorker;
import by.intexsoft.study.stringUtils.StringUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookStorageWorkerImpl  extends AbstractBookStorageWorker {
    private CSVReader reader;
    private CSVWriter writer;
    private BookParser bookParser;

    public BookStorageWorkerImpl(OperatorManager operatorManager, OrderManager orderManager, CSVReader reader, CSVWriter writer, BookParser bookParser) {
        super(operatorManager, orderManager);
        this.reader = reader;
        this.writer = writer;
        this.bookParser = bookParser;
    }

    @Override
    public Book createBook(Book book) throws IOException {
        List<Book> library = readBooks();
        long num = System.currentTimeMillis();
        String id = String.valueOf(num);
        book.setBookID(id);
        library.add(book);
        writeBooks(library);
        return book;
    }

    @Override
    public Book updateBook(Book book) throws IOException {
        List<Book> library = readBooks();
        updateBookById(library, book);
        writeBooks(library);
        return book;
    }

    @Override
    public void deleteBookById(String id) throws IOException {
        List<Book> library = readBooks();
        for(int i = 0; i < library.size(); i++){
            if (library.get(i).getBookID().equals(id)){
                library.remove(i);
                break;
            }
        }
        writeBooks(library);
    }

    @Override
    public Book findBookById(String id) throws IOException {
        return readBooks().stream().filter(x-> x.getBookID().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Book findBookByAuthorId(String id) throws IOException {
        return readBooks().stream().filter(x-> x.getAuthorID().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Book> getAllBooks() {
        return readBooks();
    }

    @Override
    public List<Book> getAllBooks(List<Filter> filters) throws IOException, NoSuchMethodException {
        List<Book> library = readBooks();
        filterBook(filters, library);
        return getBookResult(library);
    }

    @Override
    public List<Book> getAllBooks(List<Filter> filters, List<Order> orders) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Book> library = readBooks();
        filterBook(filters, library);
        orderBook(orders, library);
        return getBookResult(library);
    }

    @Override
    public List<Book> orderAllBooks(List<Order> orders) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Book> library = readBooks();
        orderBook(orders, library);
        return getBookResult(library);
    }

    private List<Book> readBooks(){
        return bookParser.toBooks(reader.readCSV());
    }

    private void writeBooks(List<Book> library) throws IOException {
        writer.writeCSV(bookParser.fromBooks(library));
    }

}
