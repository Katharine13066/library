package by.intexsoft.study.parser.impl;

import by.intexsoft.study.model.Book;
import by.intexsoft.study.parser.BookParser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CSVBookParserImpl implements BookParser {
    @Override
    public Book toBook(String line) {
        String [] parser = line.split(",");
        Book book = new Book(parser[0], parser[1], parser[2], parser[3], parser[4]);
        return book;
    }

    @Override
    public List<Book> toBooks(List<String> lines) {
        List<Book> library = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++){
            library.add(toBook(lines.get(i)));
        }
        return library;
    }

    @Override
    public String fromBook(Book book) {
        String delimeter = ",";
        StringBuilder sb = new StringBuilder("");
        String line = (sb.append(book.getBookID() + delimeter +
                book.getBookName() + delimeter +
                book.getAuthorID() + delimeter +
                book.getPublisher() + delimeter +
                book.getPublicationDate()+"\n")).toString();
        return line;
    }

    @Override
    public List<String> fromBooks(List<Book> library) {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < library.size(); i++){
            lines.add(fromBook(library.get(i)));
        }
        return lines;
    }
}
