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
import by.intexsoft.study.parser.BookParser;
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

public class BookStorageWorkerImpl implements BookStorageWorker {
    private CSVReader reader;
    private CSVWriter writer;
    private BookParser bookParser;

    public BookStorageWorkerImpl() {}

    public BookStorageWorkerImpl(CSVReader reader, CSVWriter writer, BookParser bookParser) {
        this.reader = reader;
        this.writer = writer;
        this.bookParser = bookParser;
    }

    @Override
    public Book createBook(Book book) throws IOException {
        List<String> list = reader.readCSV();
        List<Book> library = bookParser.toBooks(list);
        long num = System.currentTimeMillis();
        String id = String.valueOf(num);
        book.setBookID(id.toString());
        library.add(book);
        list = bookParser.fromBooks(library);
        writer.writeCSV(list);
        return book;
    }

    @Override
    public Book updateBook(Book book) throws IOException {
        List<String> list = reader.readCSV();
        List<Book> library = bookParser.toBooks(list);
        for(int i = 0; i < library.size(); i++){
            if (library.get(i).getBookID().equals(book.getBookID())){
                library.get(i).setBookName(book.getBookName());
                library.get(i).setAuthorID(book.getAuthorID());
                library.get(i).setPublisher(book.getPublisher());
                library.get(i).setPublicationDate(book.getPublicationDate());
            }
        }
        list = bookParser.fromBooks(library);
        writer.writeCSV(list);
        return book;
    }

    @Override
    public void deleteBookById(String id) throws IOException {
        List<String> list = reader.readCSV();
        List<Book> library = bookParser.toBooks(list);
        for(int i = 0; i < library.size(); i++){
            if (library.get(i).getBookID().equals(id)){
                library.remove(i);
            }
        }
        list = bookParser.fromBooks(library);
        writer.writeCSV(list);
    }

    @Override
    public Book findBookById(String id) throws IOException {
        List<String> list = reader.readCSV();
        List<Book> library = bookParser.toBooks(list);
        Book book = new Book();
        for(int i = 0; i < library.size(); i++){
            if (library.get(i).getBookID().equals(id)){
                book.setBookID(library.get(i).getBookID());
                book.setBookName(library.get(i).getBookName());
                book.setAuthorID(library.get(i).getAuthorID());
                book.setPublisher(library.get(i).getPublisher());
                book.setPublicationDate(library.get(i).getPublicationDate());
            }
        }
        return book;
    }

    @Override
    public Book findBookByAuthorId(String id) throws IOException {
        List<String> list = reader.readCSV();
        List<Book> library = bookParser.toBooks(list);
        Book book = new Book();
        for(int i = 0; i < library.size(); i++){
            if (library.get(i).getAuthorID().equals(id)){
                book.setBookID(library.get(i).getBookID());
                book.setBookName(library.get(i).getBookName());
                book.setAuthorID(library.get(i).getAuthorID());
                book.setPublisher(library.get(i).getPublisher());
                book.setPublicationDate(library.get(i).getPublicationDate());
            }
        }
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        List<String> list = reader.readCSV();
        List<Book> library = bookParser.toBooks(list);
        return library;
    }

    @Override
    public List<Book> getAllBooks(List<Filter> filters) throws IOException, NoSuchMethodException {
        List<Book> result = new ArrayList<>();
        List<String> list = reader.readCSV();
        List<Book> library = bookParser.toBooks(list);

        OperatorManager operatorManager = new OperatorManager();
        operatorManager.getOperatorHelper(String.class);

        for(int i = 0; i < filters.size(); i++){

            Class<Book> bookClass = Book.class;
            Method bookGetter = bookClass.getDeclaredMethod("get"+ StringUtils.firstUpperCase(filters.get(i).getField()));

            final int temp = i;
            Predicate<Book> bookPredicate = book -> {
                try {
                    String fieldValue = (String) bookGetter.invoke(book, new Object[0]);
                    IOperatorHelper<?> operatorHelper = operatorManager.getOperatorHelper(bookGetter.getReturnType());
                    OperatorHandler operatorHandler = operatorHelper.getPredicate(filters.get(temp).getOperator());
                     return  operatorHandler.handle(fieldValue, filters.get(temp).getValue());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            };

            library = library.stream().filter(bookPredicate).collect(Collectors.toList());

        }
        result.addAll(library);
        return result;
    }

    @Override
    public List<Book> getAllBooks(List<Filter> filters, List<Order> orders) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Book> result = new ArrayList<>();
        List<String> list = reader.readCSV();
        List<Book> library = bookParser.toBooks(list);

        OperatorManager operatorManager = new OperatorManager();
        operatorManager.getOperatorHelper(String.class);

        OrderManager orderManager = new OrderManager();
        orderManager.getOrderHelper(String.class);
        for(int i = 0; i < filters.size(); i++){

            Class<Book> bookClass = Book.class;
            Method bookGetter = bookClass.getDeclaredMethod("get"+ StringUtils.firstUpperCase(filters.get(i).getField()));

            final int temp = i;
            Predicate<Book> bookPredicate = book -> {
                try {
                    String fieldValue = (String) bookGetter.invoke(book, new Object[0]);
                    IOperatorHelper<?> operatorHelper = operatorManager.getOperatorHelper(bookGetter.getReturnType());
                    OperatorHandler operatorHandler = operatorHelper.getPredicate(filters.get(temp).getOperator());
                    return  operatorHandler.handle(fieldValue, filters.get(temp).getValue());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            };

            library = library.stream().filter(bookPredicate).collect(Collectors.toList());
        }

        for(int j = 0; j < orders.size(); j++){
            Class<Book> bookClass = Book.class;
            Method bookGetter = bookClass.getDeclaredMethod("get"+ StringUtils.firstUpperCase(orders.get(j).getField()));

            final int tmp = j;

            Class<IOrderTypesHelper> clazz = IOrderTypesHelper.class;
            Method comparatorMethod = clazz.getDeclaredMethod(StringUtils.getComparatorMethodName(orders.get(tmp).getOrderTypes()), new Class[]{Class.class, String.class});
            IOrderTypesHelper<?> orderTypesHelper = orderManager.getOrderHelper(bookGetter.getReturnType());
            Comparator<Book> comparator = (Comparator<Book>) comparatorMethod.invoke(orderTypesHelper, Book.class, orders.get(tmp).getField());
            library = library.stream().sorted(comparator).collect(Collectors.toList());

            };


        result.addAll(library);
        return result;
    }

    @Override
    public List<Book> orderAllBooks(List<Order> orders) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Book> result = new ArrayList<>();
        List<String> list = reader.readCSV();
        List<Book> library = bookParser.toBooks(list);

        OrderManager orderManager = new OrderManager();
        orderManager.getOrderHelper(String.class);
        for(int i = 0; i < orders.size(); i++){

            Class<Book> bookClass = Book.class;
            Method bookGetter = bookClass.getDeclaredMethod("get"+ StringUtils.firstUpperCase(orders.get(i).getField()));

            final int temp = i;

            Class<IOrderTypesHelper> clazz = IOrderTypesHelper.class;
            Method comparatorMethod = clazz.getDeclaredMethod(StringUtils.getComparatorMethodName(orders.get(i).getOrderTypes()), new Class[]{Class.class, String.class});
            IOrderTypesHelper<?> orderTypesHelper = orderManager.getOrderHelper(bookGetter.getReturnType());
            Comparator<Book> comparator = (Comparator<Book>) comparatorMethod.invoke(orderTypesHelper, Book.class, orders.get(i).getField());
            library = library.stream().sorted(comparator).collect(Collectors.toList());
        }

        result.addAll(library);
        return result;
    }

}
