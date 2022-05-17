package by.intexsoft.study.convert;

import by.intexsoft.study.model.Author;

import java.util.List;

public interface AuthorConverter {
    List<Author> authorToListConverter(Author[] authors);
}
