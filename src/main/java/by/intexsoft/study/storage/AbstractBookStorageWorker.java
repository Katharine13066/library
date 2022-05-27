package by.intexsoft.study.storage;

import by.intexsoft.study.filters.Filter;
import by.intexsoft.study.filters.IOperatorHelper;
import by.intexsoft.study.filters.OperatorHandler;
import by.intexsoft.study.filters.OperatorManager;
import by.intexsoft.study.model.Book;
import by.intexsoft.study.orders.IOrderTypesHelper;
import by.intexsoft.study.orders.Order;
import by.intexsoft.study.orders.OrderManager;
import by.intexsoft.study.orders.OrderTypes;
import by.intexsoft.study.stringUtils.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractBookStorageWorker implements  BookStorageWorker{

    private OperatorManager operatorManager;
    private OrderManager orderManager;

    public AbstractBookStorageWorker(OperatorManager operatorManager, OrderManager orderManager) {
        this.operatorManager = operatorManager;
        this.orderManager = orderManager;
        operatorManager.getOperatorHelper(String.class);
        orderManager.getOrderHelper(String.class);
    }

    protected Predicate<Book> getBookPredicate(Method bookGetter, final int temp, List<Filter> filters){
        return book -> {
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
    }

    protected Method getBookGetter(String field) throws NoSuchMethodException {
        Class<Book> bookClass = Book.class;
        return bookClass.getDeclaredMethod("get"+ StringUtils.firstUpperCase(field));
    }

    protected Method getComporatorMethod(OrderTypes field) throws NoSuchMethodException {
        Class<IOrderTypesHelper> clazz = IOrderTypesHelper.class;
        Method comparatorMethod = clazz.getDeclaredMethod(StringUtils.getComparatorMethodName(field), new Class[]{Class.class, String.class});
        return comparatorMethod;
    }

    protected Comparator<Book> getBookComporator(Method bookGetter, final int tmp, List<Order> orders) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method comparatorMethod = getComporatorMethod(orders.get(tmp).getOrderTypes());
        IOrderTypesHelper<?> orderTypesHelper = orderManager.getOrderHelper(bookGetter.getReturnType());
        Comparator<Book> comparator = (Comparator<Book>) comparatorMethod.invoke(orderTypesHelper, Book.class, orders.get(tmp).getField());
        return comparator;
    }

    protected void updateBookById(List<Book> library, Book book){
        for(Book bookIterator: library){
            if (bookIterator.getBookID().equals(book.getBookID())){
                bookIterator.setBookName(book.getBookName());
                bookIterator.setAuthorID(book.getAuthorID());
                bookIterator.setPublisher(book.getPublisher());
                bookIterator.setPublicationDate(book.getPublicationDate());
            }
        }
    }

    protected List<Book> filterBook(List<Filter> filters, List<Book> library) throws NoSuchMethodException {
        for(int i = 0; i < filters.size(); i++){
            Method bookGetter = getBookGetter(filters.get(i).getField());
            final int temp = i;
            library = library.stream().filter(getBookPredicate(bookGetter, temp, filters)).collect(Collectors.toList());

        }
        return library;
    }

    protected List<Book> orderBook(List<Order> orders, List<Book> library) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        for(int i = 0; i < orders.size(); i++){
            Method bookGetter = getBookGetter(orders.get(i).getField());
            final int temp = i;
            library = library.stream().sorted(getBookComporator(bookGetter, temp, orders)).collect(Collectors.toList());

        }
        return library;
    }

    protected List<Book> getBookResult(List<Book> library){
        List<Book> result = new ArrayList<>();
        result.addAll(library);
        return result;
    }


}
