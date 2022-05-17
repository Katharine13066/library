package by.intexsoft.study.fileUtils;

import by.intexsoft.study.model.Book;

import java.io.IOException;
import java.util.List;

public interface JSONBookReader {
    List<Book> readJSON() throws IOException;

}
