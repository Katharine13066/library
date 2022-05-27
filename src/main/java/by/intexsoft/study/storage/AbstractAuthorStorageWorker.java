package by.intexsoft.study.storage;

import by.intexsoft.study.filters.Filter;
import by.intexsoft.study.filters.IOperatorHelper;
import by.intexsoft.study.filters.OperatorHandler;
import by.intexsoft.study.filters.OperatorManager;
import by.intexsoft.study.model.Author;
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

public abstract class AbstractAuthorStorageWorker implements AuthorStorageWorker {

    private OperatorManager operatorManager;
    private OrderManager orderManager;

    public AbstractAuthorStorageWorker(OperatorManager operatorManager, OrderManager orderManager) {
        this.operatorManager = operatorManager;
        this.orderManager = orderManager;
        operatorManager.getOperatorHelper(String.class);
        orderManager.getOrderHelper(String.class);
    }
    protected Predicate<Author> getAuthorPredicate(Method authorGetter, final int temp, List<Filter> filters){
        return author -> {
            try {
                Object fieldValue = authorGetter.invoke(author, new Object[0]);
                IOperatorHelper<?> operatorHelper = operatorManager.getOperatorHelper(authorGetter.getReturnType());
                OperatorHandler operatorHandler = operatorHelper.getPredicate(filters.get(temp).getOperator());
                return  operatorHandler.handle(fieldValue, filters.get(temp).getValue());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        };
    }

    protected Method getComparatorMethod(OrderTypes field) throws NoSuchMethodException {
        Class<IOrderTypesHelper> clazz = IOrderTypesHelper.class;
        Method comparatorMethod = clazz.getDeclaredMethod(StringUtils.getComparatorMethodName(field), new Class[]{Class.class, String.class});
        return comparatorMethod;
    }
    protected Comparator<Author> getAuthorComporator(Method authorGetter, final int tmp, List<Order> orders) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method comparatorMethod = getComparatorMethod(orders.get(tmp).getOrderTypes());
        IOrderTypesHelper<?> orderTypesHelper = orderManager.getOrderHelper(authorGetter.getReturnType());
        Comparator<Author> comparator = (Comparator<Author>) comparatorMethod.invoke(orderTypesHelper, Author.class, orders.get(tmp).getField());
        return comparator;
    }

    protected Method getAuthorGetter(String field) throws NoSuchMethodException {
        Class<Author> authorClass = Author.class;
        Method authorGetter = authorClass.getDeclaredMethod("get"+ StringUtils.firstUpperCase(field));
        return authorGetter;
    }


    protected List<Author> getAuthorResult(List<Author> authorList){
        List<Author> result = new ArrayList<>();
        result.addAll(authorList);
        return result;
    }

    protected List<Author> filterAuthor(List<Filter> filters, List<Author> authorList) throws NoSuchMethodException {
        for(int i = 0; i < filters.size(); i++){
            Method authorGetter = getAuthorGetter(filters.get(i).getField());
            final int temp = i;
            authorList = authorList.stream().filter(getAuthorPredicate(authorGetter, temp, filters)).collect(Collectors.toList());

        }
        return authorList;
    }

    protected List<Author> orderAuthor(List<Order> orders, List<Author> authorList) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for(int i = 0; i < orders.size(); i++){
            Method authorGetter = getAuthorGetter(orders.get(i).getField());
            final int temp = i;
            authorList = authorList.stream().sorted(getAuthorComporator(authorGetter, temp, orders)).collect(Collectors.toList());

        }
        return authorList;
    }

    protected void updateAuthorById(List<Author> authorList, Author author){
        for(Author authorIterator: authorList){
            if(authorIterator.getAuthorID().equals(author.getAuthorID())){
                authorIterator.setAuthorName(author.getAuthorName());
                authorIterator.setPhoneNumber(author.getPhoneNumber());
                authorIterator.setAge(author.getAge());
            }
        }
    }
}
