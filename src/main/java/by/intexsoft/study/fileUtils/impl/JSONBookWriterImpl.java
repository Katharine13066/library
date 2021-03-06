package by.intexsoft.study.fileUtils.impl;

import by.intexsoft.study.fileUtils.JSONBookWriter;
import by.intexsoft.study.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class JSONBookWriterImpl implements JSONBookWriter {

    private String filename;

    public JSONBookWriterImpl(@Value("${bookJsonFilename}")String filename){
        this.filename = filename;
    }

    @Override
    public void writeJSON(List<Book> bookList) throws IOException {
        Object [] library = bookList.toArray();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filename), library);
    }
}
