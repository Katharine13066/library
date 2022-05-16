package by.intexsoft.study.storage;

import by.intexsoft.study.model.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorStorageWorker {
    Author createAuthor(Author author) throws IOException;
    Author updateAuthor(Author author) throws IOException;
    void deleteAuthorById(String id) throws IOException;
    List<Author> getAllAuthor();
}
