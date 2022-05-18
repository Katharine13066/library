package by.intexsoft.study.convert;

import by.intexsoft.study.model.Author;
import by.intexsoft.study.model.Book;

import java.util.List;

public interface BookConverter {
    List<Book> bookToListConverter(Book[] books);

}

