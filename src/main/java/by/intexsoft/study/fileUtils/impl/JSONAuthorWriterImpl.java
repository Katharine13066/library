package by.intexsoft.study.fileUtils.impl;

import by.intexsoft.study.fileUtils.JSONAuthorWriter;
import by.intexsoft.study.fileUtils.JSONBookWriter;
import by.intexsoft.study.model.Author;
import by.intexsoft.study.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONAuthorWriterImpl implements JSONAuthorWriter {

    private String filename;

    public JSONAuthorWriterImpl(String filename){
        this.filename = filename;
    }

    @Override
    public void writeJSON(List<Author> authorList) throws IOException {
        Object [] authors = authorList.toArray();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filename), authors);
    }
}
