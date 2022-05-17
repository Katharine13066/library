package by.intexsoft.study.fileUtils.impl;

import by.intexsoft.study.convert.BookConverter;
import by.intexsoft.study.convert.impl.BookConverterImpl;
import by.intexsoft.study.fileUtils.JSONBookReader;
import by.intexsoft.study.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONBookReaderImpl implements JSONBookReader {

    private String filename;

    public JSONBookReaderImpl(String filename){
        this.filename = filename;
    }

    @Override
    public  List<Book> readJSON() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Book [] library = objectMapper.readValue(new File(filename), Book[].class);
        BookConverter bookConverter = new BookConverterImpl();
        return  bookConverter.bookToListConverter(library);
    }

}
