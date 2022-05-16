package by.intexsoft.study.printer;

import by.intexsoft.study.model.Book;

import java.util.List;

public interface Printer {
    public void printBooks(List<Book> library);
    public void printStringList(List<String> list);
}
