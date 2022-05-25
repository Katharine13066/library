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

    public JSONAuthorStorageWorkerImpl(JSONAuthorReader jsonAuthorReader, JSONAuthorWriter jsonAuthorWriter) {
        this.jsonAuthorReader = jsonAuthorReader;
        this.jsonAuthorWriter = jsonAuthorWriter;
    }
    @Override
    public Author createAuthor(Author author) throws IOException {
        List<Author>authorList = jsonAuthorReader.readJSON();
        long num = System.currentTimeMillis();
        String id = String.valueOf(num);
        author.setAuthorID(id.toString());
        authorList.add(author);
        jsonAuthorWriter.writeJSON(authorList);
        return author;
    }

    @Override
    public Author updateAuthor(Author author) throws IOException {
        List<Author>authorList = jsonAuthorReader.readJSON();
        for(int i = 0; i < authorList.size(); i++){
            if(authorList.get(i).getAuthorID().equals(author.getAuthorID())){
                authorList.get(i).setAuthorName(author.getAuthorName());
                authorList.get(i).setPhoneNumber(author.getPhoneNumber());
                authorList.get(i).setAge(author.getAge());
            }
        }
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
        for (int i = 0; i < authorList.size(); i++){
            if (authorList.get(i).getAuthorID().equals(id)){
                author.setAuthorID(authorList.get(i).getAuthorID());
                author.setAuthorName(authorList.get(i).getAuthorName());
                author.setPhoneNumber(authorList.get(i).getPhoneNumber());
                author.setEmail(authorList.get(i).getEmail());
                author.setAge(authorList.get(i).getAge());
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
        List<Author> result = new ArrayList<>();
        List<Author>authorList = jsonAuthorReader.readJSON();
        OperatorManager operatorManager = new OperatorManager();
        operatorManager.getOperatorHelper(String.class);
        for(int i = 0; i < filters.size(); i++){

            Class<Author> authorClass = Author.class;
            Method authorGetter = authorClass.getDeclaredMethod("get"+ StringUtils.firstUpperCase(filters.get(i).getField()));

            final int temp = i;
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

            authorList = authorList.stream().filter(authorPredicate).collect(Collectors.toList());

        }
        result.addAll(authorList);
        return result;
    }

    @Override
    public List<Author> getAllAuthor(List<Filter> filters, List<Order> orders) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Author> result = new ArrayList<>();
        List<Author>authorList = jsonAuthorReader.readJSON();
        OperatorManager operatorManager = new OperatorManager();
        operatorManager.getOperatorHelper(String.class);

        OrderManager orderManager = new OrderManager();
        orderManager.getOrderHelper(String.class);
        for(int i = 0; i < filters.size(); i++){

            Class<Author> authorClass = Author.class;
            Method authorGetter = authorClass.getDeclaredMethod("get"+ StringUtils.firstUpperCase(filters.get(i).getField()));

            final int temp = i;
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


            authorList = authorList.stream().filter(authorPredicate).collect(Collectors.toList());
        }

        for(int j = 0; j < orders.size(); j++){
            Class<Author> authorClass = Author.class;
            Method authorGetter = authorClass.getDeclaredMethod("get"+ StringUtils.firstUpperCase(orders.get(j).getField()));


            final int tmp = j;

            Class<IOrderTypesHelper> clazz = IOrderTypesHelper.class;
            Method comparatorMethod = clazz.getDeclaredMethod(StringUtils.getComparatorMethodName(orders.get(tmp).getOrderTypes()), new Class[]{Class.class, String.class});
            IOrderTypesHelper<?> orderTypesHelper = orderManager.getOrderHelper(authorGetter.getReturnType());
            Comparator<Author> comparator = (Comparator<Author>) comparatorMethod.invoke(orderTypesHelper, Author.class, orders.get(tmp).getField());
            authorList = authorList.stream().sorted(comparator).collect(Collectors.toList());
        }

        result.addAll(authorList);
        return result;
    }

    @Override
    public List<Author> orderAllAuthor(List<Order> orders) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Author> result = new ArrayList<>();
        List<Author>authorList = jsonAuthorReader.readJSON();
        OrderManager orderManager = new OrderManager();
        orderManager.getOrderHelper(String.class);
        for(int i = 0; i < orders.size(); i++){

            Class<Author> authorClass = Author.class;
            Method authorGetter = authorClass.getDeclaredMethod("get"+ StringUtils.firstUpperCase(orders.get(i).getField()));

            final int temp = i;

            Class<IOrderTypesHelper> clazz = IOrderTypesHelper.class;
            Method comparatorMethod = clazz.getDeclaredMethod(StringUtils.getComparatorMethodName(orders.get(temp).getOrderTypes()), new Class[]{Class.class, String.class});
            IOrderTypesHelper<?> orderTypesHelper = orderManager.getOrderHelper(authorGetter.getReturnType());
            Comparator<Author> comparator = (Comparator<Author>) comparatorMethod.invoke(orderTypesHelper, Author.class, orders.get(temp).getField());
            authorList = authorList.stream().sorted(comparator).collect(Collectors.toList());

        }
        result.addAll(authorList);
        return result;
    }

}
