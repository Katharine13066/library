package by.intexsoft.study.fileUtils;

import by.intexsoft.study.model.Author;

import java.io.IOException;
import java.util.List;

public interface JSONAuthorWriter {
    public void writeJSON(List<Author> authorList) throws IOException;
}
