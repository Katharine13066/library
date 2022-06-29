package by.intexsoft.study.convert.impl;

import by.intexsoft.study.convert.AuthorConverter;
import by.intexsoft.study.model.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorConverterImpl implements AuthorConverter {

    @Override
    public List<Author> authorToListConverter(Author[] authors) {
        List<Author> authorList = new ArrayList<>();
        for (int i = 0; i < authors.length; i++){
            authorList.add(authors[i]);
        }
        return authorList;
    }
}
