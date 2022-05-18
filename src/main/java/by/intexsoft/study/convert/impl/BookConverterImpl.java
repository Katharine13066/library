package by.intexsoft.study.convert.impl;

import by.intexsoft.study.convert.BookConverter;
import by.intexsoft.study.model.Author;
import by.intexsoft.study.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookConverterImpl implements BookConverter {
    @Override
    public List<Book> bookToListConverter(Book[] books) {
        List<Book> bookList = new ArrayList<>();
        for (int i = 0; i < books.length; i++){
            bookList.add(books[i]);
        }
        return bookList;
    }

}
