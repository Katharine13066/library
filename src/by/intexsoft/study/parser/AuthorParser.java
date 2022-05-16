package by.intexsoft.study.parser;

import by.intexsoft.study.model.Author;

import java.util.List;

public interface AuthorParser {

    Author toAuthor(String line);
    List<Author> toAuthors(List<String> lines);

    String fromAuthor(Author author);
    List<String> fromAuthors(List<Author> authorsList);
}
