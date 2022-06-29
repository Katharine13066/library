package by.intexsoft.study.storage.impl;

import by.intexsoft.study.fileUtils.JSONAuthorReader;
import by.intexsoft.study.fileUtils.JSONAuthorWriter;
import by.intexsoft.study.filters.Filter;
import by.intexsoft.study.filters.OperatorManager;
import by.intexsoft.study.model.Author;
import by.intexsoft.study.orders.Order;
import by.intexsoft.study.orders.OrderManager;
import by.intexsoft.study.storage.AbstractAuthorStorageWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Component("jsonAuthorStorageWorkerImpl")
public class JSONAuthorStorageWorkerImpl extends AbstractAuthorStorageWorker {

    private JSONAuthorReader jsonAuthorReader;
    private JSONAuthorWriter jsonAuthorWriter;

    @Autowired
    public JSONAuthorStorageWorkerImpl(OperatorManager operatorManager, OrderManager orderManager, JSONAuthorReader jsonAuthorReader, JSONAuthorWriter jsonAuthorWriter) {
        super(operatorManager, orderManager);
        this.jsonAuthorReader = jsonAuthorReader;
        this.jsonAuthorWriter = jsonAuthorWriter;
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
                break;
            }
        }
        jsonAuthorWriter.writeJSON(authorList);
    }

    @Override
    public Author findAuthorById(String id) throws IOException {
        return jsonAuthorReader.readJSON().stream().filter(x -> x.getAuthorID().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Author> getAllAuthor() throws IOException {
        return jsonAuthorReader.readJSON();
    }

    @Override
    public List<Author> getAllAuthor(List<Filter> filters) throws IOException, NoSuchMethodException {
        List<Author>authorList = jsonAuthorReader.readJSON();
        filterAuthor(filters, authorList);
        return getAuthorResult(authorList);
    }

    @Override
    public List<Author> getAllAuthor(List<Filter> filters, List<Order> orders) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Author>authorList = jsonAuthorReader.readJSON();
        filterAuthor(filters, authorList);
        orderAuthor(orders, authorList);
        return getAuthorResult(authorList);
    }

    @Override
    public List<Author> orderAllAuthor(List<Order> orders) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Author>authorList = jsonAuthorReader.readJSON();
        orderAuthor(orders, authorList);
        return getAuthorResult(authorList);
    }

}
