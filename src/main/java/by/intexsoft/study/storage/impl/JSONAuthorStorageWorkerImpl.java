package by.intexsoft.study.storage.impl;

import by.intexsoft.study.fileUtils.JSONAuthorReader;
import by.intexsoft.study.fileUtils.JSONAuthorWriter;
import by.intexsoft.study.model.Author;
import by.intexsoft.study.storage.AuthorStorageWorker;

import java.io.IOException;
import java.util.List;

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
            }
        }
        return author;
    }

    @Override
    public List<Author> getAllAuthor() throws IOException {
        return jsonAuthorReader.readJSON();
    }
}
