package by.intexsoft.study.storage.impl;

import by.intexsoft.study.fileUtils.CSVReader;
import by.intexsoft.study.fileUtils.CSVWriter;
import by.intexsoft.study.model.Book;
import by.intexsoft.study.parser.BookParser;
import by.intexsoft.study.storage.StorageWorker;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public class StorageWorkerImpl implements StorageWorker {

    private CSVReader reader;
    private CSVWriter writer;
    private BookParser bookParser;

    public StorageWorkerImpl() {}

    public StorageWorkerImpl(CSVReader reader, CSVWriter writer, BookParser bookParser) {
        this.reader = reader;
        this.writer = writer;
        this.bookParser = bookParser;
    }

    @Override
    public Book createBook(Book book) throws IOException {
       List<String> list = reader.readCSV();
       List<Book> library = bookParser.toBooks(list);
       long num = System.currentTimeMillis();
       String id = String.valueOf(num);
       book.setId(id.toString());
       library.add(book);
       list = bookParser.fromBooks(library);
       writer.writeCSV(list);
       return book;
    }

    @Override
    public Book updateBook(Book book) throws IOException {
        List<String> list = reader.readCSV();
        List<Book> library = bookParser.toBooks(list);
        for(int i = 0; i < library.size(); i++){
            if (library.get(i).getId().equals(book.getId())){
                library.get(i).setName(book.getName());
                library.get(i).setAuthor(book.getAuthor());
                library.get(i).setPublisher(book.getPublisher());
                library.get(i).setPublicationDate(book.getPublicationDate());
            }
        }
        list = bookParser.fromBooks(library);
        writer.writeCSV(list);
        return book;
    }

    @Override
    public void deleteBookById(String id) throws IOException {
        List<String> list = reader.readCSV();
        List<Book> library = bookParser.toBooks(list);
        for(int i = 0; i < library.size(); i++){
            if (library.get(i).getId().equals(id)){
               library.remove(i);
            }
        }
        list = bookParser.fromBooks(library);
        writer.writeCSV(list);
    }

    @Override
    public List<Book> getAllBooks() {
        List<String> list = reader.readCSV();
        List<Book> library = bookParser.toBooks(list);
        return library;
    }

}
