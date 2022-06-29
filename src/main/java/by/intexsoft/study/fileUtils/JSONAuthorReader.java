package by.intexsoft.study.fileUtils;

import by.intexsoft.study.model.Author;
import by.intexsoft.study.model.Book;

import java.io.IOException;
import java.util.List;

public interface JSONAuthorReader {

    List<Author> readJSON() throws IOException;
}
