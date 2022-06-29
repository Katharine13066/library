package by.intexsoft.study.printer.impl;

import by.intexsoft.study.model.Author;
import by.intexsoft.study.printer.AuthorPrinter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorPrinterImpl implements AuthorPrinter {
    @Override
    public void printAuthors(List<Author> authorsList) {
        for (int i = 0; i < authorsList.size(); i++){
            System.out.println(authorsList.get(i));
        }
    }
}
