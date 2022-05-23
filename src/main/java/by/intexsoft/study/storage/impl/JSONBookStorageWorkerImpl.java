package by.intexsoft.study.storage.impl;

import by.intexsoft.study.fileUtils.JSONBookReader;
import by.intexsoft.study.fileUtils.JSONBookWriter;
import by.intexsoft.study.filters.Filter;
import by.intexsoft.study.filters.OperatorHelper;
import by.intexsoft.study.model.Book;
import by.intexsoft.study.orders.Order;
import by.intexsoft.study.orders.OrderTypesBookHelper;
import by.intexsoft.study.storage.BookStorageWorker;
import by.intexsoft.study.stringUtils.StringUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JSONBookStorageWorkerImpl implements BookStorageWorker {

    private JSONBookReader jsonBookReader;
    private JSONBookWriter jsonBookWriter;

    public JSONBookStorageWorkerImpl(JSONBookReader jsonBookReader, JSONBookWriter jsonBookWriter) {
        this.jsonBookReader = jsonBookReader;
        this.jsonBookWriter = jsonBookWriter;
    }

    @Override
    public Book createBook(Book book) throws IOException {
        List<Book> library = jsonBookReader.readJSON();
        long num = System.currentTimeMillis();
        String id = String.valueOf(num);
        book.setBookID(id.toString());
        library.add(book);
        jsonBookWriter.writeJSON(library);
        return book;
    }

    @Override
    public Book updateBook(Book book) throws IOException {
        List<Book> library = jsonBookReader.readJSON();
        for(int i = 0; i < library.size(); i++){
            if (library.get(i).getBookID().equals(book.getBookID())){
                library.get(i).setBookName(book.getBookName());
                library.get(i).setAuthorID(book.getAuthorID());
                library.get(i).setPublisher(book.getPublisher());
                library.get(i).setPublicationDate(book.getPublicationDate());
            }
        }
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
        for (int i = 0; i < library.size(); i++){
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
        List<Book> library = jsonBookReader.readJSON();
        Book book = new Book();
        for (int i = 0; i < library.size(); i++){
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
    public List<Book> getAllBooks() throws IOException {
        return jsonBookReader.readJSON();
    }

    @Override
    public List<Book> getAllBooks(List<Filter> filters) throws IOException, NoSuchMethodException {
        List<Book> result = new ArrayList<>();
        List<Book> library = jsonBookReader.readJSON();
        for(int i = 0; i < filters.size(); i++){

            Class<Book> bookClass = Book.class;
            Method bookGetter = bookClass.getDeclaredMethod("get"+ StringUtils.firstUpperCase(filters.get(i).getField()));

            final int temp = i;
            Predicate<Book> bookPredicate = book -> {
                try {
                    String fieldValue = (String) bookGetter.invoke(book, new Object[0]);
                    return OperatorHelper.getPredicate(filters.get(temp).getOperator()).handle(fieldValue,filters.get(temp).getValue());
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
    public List<Book> getAllBooks(List<Filter> filters, List<Order> orders) throws IOException, NoSuchMethodException {
        List<Book> result = new ArrayList<>();
        List<Book> library = jsonBookReader.readJSON();
        for(int i = 0; i < filters.size(); i++){

            Class<Book> bookClass = Book.class;
            Method bookGetter = bookClass.getDeclaredMethod("get"+ StringUtils.firstUpperCase(filters.get(i).getField()));

            final int temp = i;
            Predicate<Book> bookPredicate = book -> {
                try {
                    String fieldValue = (String) bookGetter.invoke(book, new Object[0]);
                    return OperatorHelper.getPredicate(filters.get(temp).getOperator()).handle(fieldValue,filters.get(temp).getValue());
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
            Method bookGetter = bookClass.getDeclaredMethod("get"+ StringUtils.firstUpperCase(filters.get(j).getField()));

            final int tmp = j;
            Function<Book, String> function = book -> {
                try {
                    return (String) bookGetter.invoke(book);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            };

            library = library.stream().sorted(OrderTypesBookHelper.getOrder(orders.get(tmp).getOrderTypes()).handle(function))
                    .collect(Collectors.toList());
        }

        result.addAll(library);
        return result;
    }

    @Override
    public List<Book> orderAllBooks(List<Order> orders) throws NoSuchMethodException, IOException {
        List<Book> result = new ArrayList<>();
        List<Book> library = jsonBookReader.readJSON();
        for(int i = 0; i < orders.size(); i++){

            Class<Book> bookClass = Book.class;
            Method bookGetter = bookClass.getDeclaredMethod("get"+ StringUtils.firstUpperCase(orders.get(i).getField()));

            final int temp = i;

            Function<Book, String> function = book -> {
                try {
                    return (String) bookGetter.invoke(book);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            };

            library = library.stream().sorted(OrderTypesBookHelper.getOrder(orders.get(temp).getOrderTypes()).handle(function)).collect(Collectors.toList());

        }
        result.addAll(library);
        return result;
    }
}
