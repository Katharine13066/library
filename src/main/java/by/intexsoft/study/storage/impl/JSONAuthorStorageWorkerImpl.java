package by.intexsoft.study.storage.impl;

import by.intexsoft.study.fileUtils.JSONAuthorReader;
import by.intexsoft.study.fileUtils.JSONAuthorWriter;
import by.intexsoft.study.filters.Filter;
import by.intexsoft.study.filters.IOperatorHelper;
import by.intexsoft.study.filters.OperatorHandler;
import by.intexsoft.study.filters.OperatorManager;
import by.intexsoft.study.model.Author;
import by.intexsoft.study.orders.IOrderTypesHelper;
import by.intexsoft.study.orders.Order;
import by.intexsoft.study.orders.OrderManager;
import by.intexsoft.study.orders.OrderTypes;
import by.intexsoft.study.storage.AuthorStorageWorker;
import by.intexsoft.study.stringUtils.StringUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JSONAuthorStorageWorkerImpl implements AuthorStorageWorker {

    private JSONAuthorReader jsonAuthorReader;
    private JSONAuthorWriter jsonAuthorWriter;
    private OperatorManager operatorManager;
    private OrderManager orderManager;


    public JSONAuthorStorageWorkerImpl(JSONAuthorReader jsonAuthorReader, JSONAuthorWriter jsonAuthorWriter) {
        this.jsonAuthorReader = jsonAuthorReader;
        this.jsonAuthorWriter = jsonAuthorWriter;
    }

    public JSONAuthorStorageWorkerImpl(JSONAuthorReader jsonAuthorReader, JSONAuthorWriter jsonAuthorWriter, OperatorManager operatorManager, OrderManager orderManager) {
        this.jsonAuthorReader = jsonAuthorReader;
        this.jsonAuthorWriter = jsonAuthorWriter;
        this.operatorManager = operatorManager;
        this.orderManager = orderManager;
        operatorManager.getOperatorHelper(String.class);
        orderManager.getOrderHelper(String.class);
    }

    @Override
    public Author createAuthor(Author author) throws IOException {
        List<Author>authorList = jsonAuthorReader.readJSON();
        long num = System.currentTimeMillis();
        String id = String.valueOf(num);
        author.setAuthorID(id);
        authorList.add(author);
        jsonAuthorWriter.writeJSON(authorList);
        return author;
    }

    @Override
    public Author updateAuthor(Author author) throws IOException {
        List<Author>authorList = jsonAuthorReader.readJSON();
        updateAuthorById(authorList, author);
        jsonAuthorWriter.writeJSON(authorList);
        return author;
    }

    @Override
    public void deleteAuthorById(String id) throws IOException {
        List<Author>authorList = jsonAuthorReader.readJSON();
        for (int i = 0; i < authorList.size(); i++){
            if(authorList.get(i).getAuthorID().equals(id)){
                authorList.remove(i);
            }
        }
        jsonAuthorWriter.writeJSON(authorList);
    }

    @Override
    public Author findAuthorById(String id) throws IOException {
        List<Author>authorList = jsonAuthorReader.readJSON();
        Author author = new Author();
        for (Author authorIterator: authorList){
            if (authorIterator.getAuthorID().equals(id)){
                author.setAuthorID(authorIterator.getAuthorID());
                author.setAuthorName(authorIterator.getAuthorName());
                author.setPhoneNumber(authorIterator.getPhoneNumber());
                author.setEmail(authorIterator.getEmail());
                author.setAge(authorIterator.getAge());
            }
        }
        return author;
    }

    @Override
    public List<Author> getAllAuthor() throws IOException {
        return jsonAuthorReader.readJSON();
    }

    @Override
    public List<Author> getAllAuthor(List<Filter> filters) throws IOException, NoSuchMethodException {
        List<Author>authorList = jsonAuthorReader.readJSON();
        for(int i = 0; i < filters.size(); i++){
            Method authorGetter = getAuthorGetter(filters.get(i).getField());
            final int temp = i;
            authorList = authorList.stream().filter(getAuthorPredicate(authorGetter, temp, filters)).collect(Collectors.toList());

        }
        return getAuthorResult(authorList);
    }

    @Override
    public List<Author> getAllAuthor(List<Filter> filters, List<Order> orders) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Author>authorList = jsonAuthorReader.readJSON();
        for(int i = 0; i < filters.size(); i++){
            Method authorGetter = getAuthorGetter(filters.get(i).getField());
            final int temp = i;
            authorList = authorList.stream().filter(getAuthorPredicate(authorGetter, temp, filters)).collect(Collectors.toList());
        }

        for(int j = 0; j < orders.size(); j++){
            Method authorGetter =getAuthorGetter(orders.get(j).getField());
            final int tmp = j;
            authorList = authorList.stream().sorted(getAuthorComporator(authorGetter, tmp, orders)).collect(Collectors.toList());
        }
        return getAuthorResult(authorList);
    }

    @Override
    public List<Author> orderAllAuthor(List<Order> orders) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Author>authorList = jsonAuthorReader.readJSON();
        for(int i = 0; i < orders.size(); i++){
            Method authorGetter = getAuthorGetter(orders.get(i).getField());
            final int temp = i;
            authorList = authorList.stream().sorted(getAuthorComporator(authorGetter, temp, orders)).collect(Collectors.toList());

        }
        return getAuthorResult(authorList);
    }

    private List<Author> getAuthorResult(List<Author> authorList){
        List<Author> result = new ArrayList<>();
        result.addAll(authorList);
        return result;
    }


    private void updateAuthorById(List<Author> authorList, Author author){
        for(Author authorIterator: authorList){
            if(authorIterator.getAuthorID().equals(author.getAuthorID())){
                authorIterator.setAuthorName(author.getAuthorName());
                authorIterator.setPhoneNumber(author.getPhoneNumber());
                authorIterator.setAge(author.getAge());
            }
        }
    }

    private Method getAuthorGetter(String field) throws NoSuchMethodException {
        Class<Author> authorClass = Author.class;
        Method authorGetter = authorClass.getDeclaredMethod("get"+ StringUtils.firstUpperCase(field));
        return authorGetter;
    }

    private Predicate<Author> getAuthorPredicate(Method authorGetter, final int temp, List<Filter> filters){
        Predicate<Author> authorPredicate = author -> {
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
        return authorPredicate;
    }

    private Method getComporatorMethod(OrderTypes field) throws NoSuchMethodException {
        Class<IOrderTypesHelper> clazz = IOrderTypesHelper.class;
        Method comparatorMethod = clazz.getDeclaredMethod(StringUtils.getComparatorMethodName(field), new Class[]{Class.class, String.class});
        return comparatorMethod;
    }

    private Comparator<Author> getAuthorComporator(Method authorGetter, final int tmp, List<Order> orders) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method comparatorMethod = getComporatorMethod(orders.get(tmp).getOrderTypes());
        IOrderTypesHelper<?> orderTypesHelper = orderManager.getOrderHelper(authorGetter.getReturnType());
        Comparator<Author> comparator = (Comparator<Author>) comparatorMethod.invoke(orderTypesHelper, Author.class, orders.get(tmp).getField());
        return comparator;
    }
}
