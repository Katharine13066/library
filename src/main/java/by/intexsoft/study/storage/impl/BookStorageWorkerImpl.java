package by.intexsoft.study.storage.impl;

import by.intexsoft.study.fileUtils.CSVReader;
import by.intexsoft.study.fileUtils.CSVWriter;
import by.intexsoft.study.model.Book;
import by.intexsoft.study.parser.BookParser;
import by.intexsoft.study.storage.BookStorageWorker;

import java.io.IOException;
import java.util.List;

public class BookStorageWorkerImpl implements BookStorageWorker {
    private CSVReader reader;
    private CSVWriter writer;
    private BookParser bookParser;

    public BookStorageWorkerImpl() {}

    public BookStorageWorkerImpl(CSVReader reader, CSVWriter writer, BookParser bookParser) {
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
        book.setBookID(id.toString());
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
            if (library.get(i).getBookID().equals(book.getBookID())){
                library.get(i).setBookName(book.getBookName());
                library.get(i).setAuthorID(book.getAuthorID());
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
            if (library.get(i).getBookID().equals(id)){
                library.remove(i);
            }
        }
        list = bookParser.fromBooks(library);
        writer.writeCSV(list);
    }

    @Override
    public Book findBookById(String id) throws IOException {
        List<String> list = reader.readCSV();
        List<Book> library = bookParser.toBooks(list);
        Book book = new Book();
        for(int i = 0; i < library.size(); i++){
            if (library.get(i).getBookID().equals(id)){
                book.setBookID(library.get(i).getBookID());
                book.setBookName(library.get(i).getBookName());
                book.setAuthorID(library.get(i).getAuthorID());
                book.setPublisher(library.get(i).getPublisher());
                book.setPublicationDate(library.get(i).getPublicationDate());
            }
        }
        return book;
    }

    @Override
    public Book findBookByAuthorId(String id) throws IOException {
        List<String> list = reader.readCSV();
        List<Book> library = bookParser.toBooks(list);
        Book book = new Book();
        for(int i = 0; i < library.size(); i++){
            if (library.get(i).getAuthorID().equals(id)){
                book.setBookID(library.get(i).getBookID());
                book.setBookName(library.get(i).getBookName());
                book.setAuthorID(library.get(i).getAuthorID());
                book.setPublisher(library.get(i).getPublisher());
                book.setPublicationDate(library.get(i).getPublicationDate());
            }
        }
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        List<String> list = reader.readCSV();
        List<Book> library = bookParser.toBooks(list);
        return library;
    }
}
