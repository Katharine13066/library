package by.intexsoft.study.storage.impl;

import by.intexsoft.study.fileUtils.CSVReader;
import by.intexsoft.study.fileUtils.CSVWriter;
import by.intexsoft.study.filters.Filter;
import by.intexsoft.study.filters.OperatorManager;
import by.intexsoft.study.model.Author;
import by.intexsoft.study.orders.Order;
import by.intexsoft.study.orders.OrderManager;
import by.intexsoft.study.parser.AuthorParser;
import by.intexsoft.study.storage.AbstractAuthorStorageWorker;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class AuthorStorageWorkerImpl extends AbstractAuthorStorageWorker {

    private CSVReader reader;
    private CSVWriter writer;
    private AuthorParser authorParser;

    public AuthorStorageWorkerImpl(OperatorManager operatorManager, OrderManager orderManager, CSVReader reader, CSVWriter writer, AuthorParser authorParser) {
        super(operatorManager, orderManager);
        this.reader = reader;
        this.writer = writer;
        this.authorParser = authorParser;
    }

    @Override
    public Author createAuthor(Author author) throws IOException {
        List<Author>authorList = readAuthors();
        long num = System.currentTimeMillis();
        String id = String.valueOf(num);
        author.setAuthorID(id);
        authorList.add(author);
        writeAuthors(authorList);
        return author;
    }

    @Override
    public Author updateAuthor(Author author) throws IOException {
        List<Author>authorList = readAuthors();
        updateAuthorById(authorList, author);
        writeAuthors(authorList);
        return author;
    }

    @Override
    public void deleteAuthorById(String id) throws IOException {
        List<Author>authorList = readAuthors();
        for (int i = 0; i < authorList.size(); i++){
            if(authorList.get(i).getAuthorID().equals(id)){
                authorList.remove(i);
                break;
            }
        }
        writeAuthors(authorList);
    }

    @Override
    public Author findAuthorById(String id) throws IOException {
        return readAuthors().stream().filter(x -> x.getAuthorID().equals(id)).findFirst().orElse(null);
    }


    @Override
    public List<Author> getAllAuthor() {
        return readAuthors();
    }

    @Override
    public List<Author> getAllAuthor(List<Filter> filters) throws NoSuchMethodException {
        List<Author>authorList = readAuthors();
        filterAuthor(filters, authorList);
        return getAuthorResult(authorList);
    }

    @Override
    public List<Author> getAllAuthor(List<Filter> filters, List<Order> orders) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Author>authorList = readAuthors();
        filterAuthor(filters, authorList);
        orderAuthor(orders, authorList);
        return getAuthorResult(authorList);
    }

    @Override
    public List<Author> orderAllAuthor(List<Order> orders) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Author>authorList = readAuthors();
        orderAuthor(orders, authorList);
        return getAuthorResult(authorList);
    }

    private List<Author> readAuthors(){
        return authorParser.toAuthors(reader.readCSV());
    }

    private void writeAuthors(List<Author> authorList) throws IOException {
        writer.writeCSV(authorParser.fromAuthors(authorList));
    }

}