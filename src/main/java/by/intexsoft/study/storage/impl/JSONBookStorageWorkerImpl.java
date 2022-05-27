package by.intexsoft.study.storage.impl;

import by.intexsoft.study.fileUtils.JSONBookReader;
import by.intexsoft.study.fileUtils.JSONBookWriter;
import by.intexsoft.study.filters.Filter;
import by.intexsoft.study.filters.IOperatorHelper;
import by.intexsoft.study.filters.OperatorHandler;
import by.intexsoft.study.filters.OperatorManager;
import by.intexsoft.study.model.Book;
import by.intexsoft.study.orders.IOrderTypesHelper;
import by.intexsoft.study.orders.Order;
import by.intexsoft.study.orders.OrderManager;
import by.intexsoft.study.orders.OrderTypes;
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

public class JSONBookStorageWorkerImpl implements BookStorageWorker {

    private JSONBookReader jsonBookReader;
    private JSONBookWriter jsonBookWriter;
    private OperatorManager operatorManager;
    private OrderManager orderManager;

    public JSONBookStorageWorkerImpl(JSONBookReader jsonBookReader, JSONBookWriter jsonBookWriter) {
        this.jsonBookReader = jsonBookReader;
        this.jsonBookWriter = jsonBookWriter;
    }

    public JSONBookStorageWorkerImpl(JSONBookReader jsonBookReader, JSONBookWriter jsonBookWriter, OperatorManager operatorManager, OrderManager orderManager) {
        this.jsonBookReader = jsonBookReader;
        this.jsonBookWriter = jsonBookWriter;
        this.operatorManager = operatorManager;
        this.orderManager = orderManager;
        orderManager.getOrderHelper(String.class);
        operatorManager.getOperatorHelper(String.class);

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

        for(int i = 0; i < filters.size(); i++){
            Method bookGetter = getBookGetter(filters.get(i).getField());
            final int temp = i;
            library = library.stream().filter(getBookPredicate(bookGetter, temp, filters)).collect(Collectors.toList());

        }
        return getBookResult(library);
    }

    @Override
    public List<Book> getAllBooks(List<Filter> filters, List<Order> orders) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Book> library = jsonBookReader.readJSON();

        for(int i = 0; i < filters.size(); i++){
            Method bookGetter = getBookGetter(filters.get(i).getField());
            final int temp = i;
            library = library.stream().filter(getBookPredicate(bookGetter, temp, filters)).collect(Collectors.toList());
        }

        for(int j = 0; j < orders.size(); j++){
            Method bookGetter = getBookGetter(orders.get(j).getField());
            final int tmp = j;
            library = library.stream().sorted(getBookComporator(bookGetter, tmp, orders)).collect(Collectors.toList());
        }

        return getBookResult(library);
    }

    @Override
    public List<Book> orderAllBooks(List<Order> orders) throws NoSuchMethodException, IOException, InvocationTargetException, IllegalAccessException {
        List<Book> library = jsonBookReader.readJSON();
        for(int i = 0; i < orders.size(); i++){
            Method bookGetter = getBookGetter(orders.get(i).getField());
            final int temp = i;
            library = library.stream().sorted(getBookComporator(bookGetter,temp, orders)).collect(Collectors.toList());
        }
        return getBookResult(library);
    }

    private List<Book> getBookResult(List<Book> library){
        List<Book> result = new ArrayList<>();
        result.addAll(library);
        return result;
    }

    private void updateBookById(List<Book> library, Book book){
        for(Book bookIterator: library){
            if (bookIterator.getBookID().equals(book.getBookID())){
                bookIterator.setBookName(book.getBookName());
                bookIterator.setAuthorID(book.getAuthorID());
                bookIterator.setPublisher(book.getPublisher());
                bookIterator.setPublicationDate(book.getPublicationDate());
            }
        }
    }

    private Method getBookGetter(String field) throws NoSuchMethodException {
        Class<Book> bookClass = Book.class;
        return bookClass.getDeclaredMethod("get"+ StringUtils.firstUpperCase(field));
    }

    private Predicate<Book> getBookPredicate(Method bookGetter, final int temp, List<Filter> filters){
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
        return bookPredicate;
    }

    private Method getComporatorMethod(OrderTypes field) throws NoSuchMethodException {
        Class<IOrderTypesHelper> clazz = IOrderTypesHelper.class;
        Method comparatorMethod = clazz.getDeclaredMethod(StringUtils.getComparatorMethodName(field), new Class[]{Class.class, String.class});
        return comparatorMethod;
    }

    private Comparator<Book> getBookComporator(Method bookGetter, final int tmp, List<Order> orders) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method comparatorMethod = getComporatorMethod(orders.get(tmp).getOrderTypes());
        IOrderTypesHelper<?> orderTypesHelper = orderManager.getOrderHelper(bookGetter.getReturnType());
        Comparator<Book> comparator = (Comparator<Book>) comparatorMethod.invoke(orderTypesHelper, Book.class, orders.get(tmp).getField());
        return comparator;
    }
}
