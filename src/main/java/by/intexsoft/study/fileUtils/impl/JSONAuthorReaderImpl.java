package by.intexsoft.study.fileUtils.impl;

import by.intexsoft.study.convert.AuthorConverter;
import by.intexsoft.study.convert.BookConverter;
import by.intexsoft.study.convert.impl.AuthorConverterImpl;
import by.intexsoft.study.convert.impl.BookConverterImpl;
import by.intexsoft.study.fileUtils.JSONAuthorReader;
import by.intexsoft.study.model.Author;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONAuthorReaderImpl implements JSONAuthorReader {

    private String filename;

    public JSONAuthorReaderImpl(String filename){
        this.filename = filename;
    }

    @Override
    public List<Author> readJSON() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Author[] authors = objectMapper.readValue(new File(filename), Author[].class);
        AuthorConverter authorConverter = new AuthorConverterImpl();
        return  authorConverter.authorToListConverter(authors);
    }
}
