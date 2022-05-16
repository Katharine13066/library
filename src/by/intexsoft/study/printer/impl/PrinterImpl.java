package by.intexsoft.study.printer.impl;

import by.intexsoft.study.model.Book;
import by.intexsoft.study.printer.Printer;

import java.util.List;

public class PrinterImpl implements Printer {

    @Override
    public void printStringList(List<String> list) {
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }

    }

    public void printBooks(List<Book> library){
        for (int i = 0; i < library.size(); i++){
            System.out.println(library.get(i));
        }
    }
}
