package by.intexsoft.study.parser;

import by.intexsoft.study.model.Book;

import java.util.List;

public interface BookParser {

    Book toBook(String line);
    List<Book> toBooks(List<String> lines);

    String fromBook(Book book);
    List<String> fromBooks(List<Book> library);
}
