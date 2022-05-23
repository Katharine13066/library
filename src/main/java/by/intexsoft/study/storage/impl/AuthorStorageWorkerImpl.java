package by.intexsoft.study.storage.impl;

import by.intexsoft.study.fileUtils.CSVReader;
import by.intexsoft.study.fileUtils.CSVWriter;
import by.intexsoft.study.filters.Filter;
import by.intexsoft.study.filters.OperatorHelper;
import by.intexsoft.study.model.Author;
import by.intexsoft.study.orders.Order;
import by.intexsoft.study.orders.OrderTypesAuthorHelper;
import by.intexsoft.study.parser.AuthorParser;
import by.intexsoft.study.storage.AuthorStorageWorker;
import by.intexsoft.study.stringUtils.StringUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AuthorStorageWorkerImpl implements AuthorStorageWorker {

    private CSVReader reader;
    private CSVWriter writer;
    private AuthorParser authorParser;

    public AuthorStorageWorkerImpl(){}

    public AuthorStorageWorkerImpl(CSVReader reader, CSVWriter writer, AuthorParser authorParser) {
        this.reader = reader;
        this.writer = writer;
        this.authorParser = authorParser;
    }

    @Override
    public Author createAuthor(Author author) throws IOException {
        List<String> list = reader.readCSV();
        List<Author>authorList = authorParser.toAuthors(list);
        long num = System.currentTimeMillis();
        String id = String.valueOf(num);
        author.setAuthorID(id.toString());
        authorList.add(author);
        list = authorParser.fromAuthors(authorList);
        writer.writeCSV(list);
        return author;
    }

    @Override
    public Author updateAuthor(Author author) throws IOException {
        List<String> list = reader.readCSV();
        List<Author>authorList = authorParser.toAuthors(list);
        for(int i = 0; i < authorList.size(); i++){
            if(authorList.get(i).getAuthorID().equals(author.getAuthorID())){
                authorList.get(i).setAuthorName(author.getAuthorName());
                authorList.get(i).setPhoneNumber(author.getPhoneNumber());
                authorList.get(i).setAge(author.getAge());
            }
        }
        list = authorParser.fromAuthors(authorList);
        writer.writeCSV(list);
        return author;
    }

    @Override
    public void deleteAuthorById(String id) throws IOException {
        List<String> list = reader.readCSV();
        List<Author>authorList = authorParser.toAuthors(list);
        for (int i = 0; i < authorList.size(); i++){
            if(authorList.get(i).getAuthorID().equals(id)){
                authorList.remove(i);
            }
        }
        list = authorParser.fromAuthors(authorList);
        writer.writeCSV(list);
    }

    @Override
    public Author findAuthorById(String id) throws IOException {
        List<String> list = reader.readCSV();
        List<Author> authorList = authorParser.toAuthors(list);
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
    public List<Author> getAllAuthor() {
        List<String> list = reader.readCSV();
        List<Author>authorList = authorParser.toAuthors(list);
        return authorList;
    }

    @Override
    public List<Author> getAllAuthor(List<Filter> filters) throws IOException, NoSuchMethodException {
        List<Author> result = new ArrayList<>();
        List<String> list = reader.readCSV();
        List<Author>authorList = authorParser.toAuthors(list);
        for(int i = 0; i < filters.size(); i++){

            Class<Author> authorClass = Author.class;
            Method authorGetter = authorClass.getDeclaredMethod("get"+ StringUtils.firstUpperCase(filters.get(i).getField()));

            final int temp = i;
            Predicate<Author> authorPredicate = author -> {
                try {
                    String fieldValue = (String) authorGetter.invoke(author, new Object[0]);
                    return OperatorHelper.getPredicate(filters.get(temp).getOperator()).handle(fieldValue,filters.get(temp).getValue());
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
    public List<Author> getAllAuthor(List<Filter> filters, List<Order> orders) throws IOException, NoSuchMethodException {
        List<Author> result = new ArrayList<>();
        List<String> list = reader.readCSV();
        List<Author>authorList = authorParser.toAuthors(list);
        for(int i = 0; i < filters.size(); i++){

            Class<Author> authorClass = Author.class;
            Method authorGetter = authorClass.getDeclaredMethod("get"+ StringUtils.firstUpperCase(filters.get(i).getField()));

            final int temp = i;
            Predicate<Author> authorPredicate = author -> {
                try {
                    String fieldValue = (String) authorGetter.invoke(author, new Object[0]);
                    return OperatorHelper.getPredicate(filters.get(temp).getOperator()).handle(fieldValue,filters.get(temp).getValue());
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
            Method authorGetter = authorClass.getDeclaredMethod("get"+ StringUtils.firstUpperCase(filters.get(j).getField()));


            final int tmp = j;
            Function<Author, String> function = author -> {
                try {
                    return (String) authorGetter.invoke(author);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            };

            authorList = authorList.stream().sorted(OrderTypesAuthorHelper.getOrder(orders.get(tmp).getOrderTypes()).handle(function))
                    .collect(Collectors.toList());
        }

        result.addAll(authorList);
        return result;
    }

    @Override
    public List<Author> orderAllAuthor(List<Order> orders) throws IOException, NoSuchMethodException {
        List<Author> result = new ArrayList<>();
        List<String> list = reader.readCSV();
        List<Author>authorList = authorParser.toAuthors(list);
        for(int i = 0; i < orders.size(); i++){

            Class<Author> authorClass = Author.class;
            Method authorGetter = authorClass.getDeclaredMethod("get"+ StringUtils.firstUpperCase(orders.get(i).getField()));

            final int temp = i;

            Function<Author, String> function = author -> {
                try {
                    return (String) authorGetter.invoke(author);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            };

            authorList = authorList.stream().sorted(OrderTypesAuthorHelper.getOrder(orders.get(temp).getOrderTypes()).handle(function)).collect(Collectors.toList());

        }
        result.addAll(authorList);
        return result;
    }

}