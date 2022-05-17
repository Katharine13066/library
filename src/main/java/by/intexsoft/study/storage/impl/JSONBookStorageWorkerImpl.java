package by.intexsoft.study.storage.impl;

import by.intexsoft.study.fileUtils.JSONBookReader;
import by.intexsoft.study.fileUtils.JSONBookWriter;
import by.intexsoft.study.model.Book;
import by.intexsoft.study.storage.BookStorageWorker;

import java.io.IOException;
import java.util.List;

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
        jsonBookWriter.writeJSON(library);
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
        jsonBookWriter.writeJSON(library);
        return book;
    }

    @Override
    public List<Book> getAllBooks() throws IOException {
        List<Book> library = jsonBookReader.readJSON();
        return library;
    }
}
