package by.intexsoft.study.printer.impl;

import by.intexsoft.study.model.Book;
import by.intexsoft.study.printer.BookPrinter;

import java.util.List;

public class BookPrinterImpl implements BookPrinter {

    public void printBooks(List<Book> library){
        for (int i = 0; i < library.size(); i++){
            System.out.println(library.get(i));
        }
    }
}
