package by.intexsoft.study.storage.impl;

import by.intexsoft.study.fileUtils.CSVReader;
import by.intexsoft.study.fileUtils.CSVWriter;
import by.intexsoft.study.model.Author;
import by.intexsoft.study.parser.AuthorParser;
import by.intexsoft.study.storage.AuthorStorageWorker;

import java.io.IOException;
import java.util.List;

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
    public List<Author> getAllAuthor() {
        List<String> list = reader.readCSV();
        List<Author>authorList = authorParser.toAuthors(list);
        return authorList;
    }
}