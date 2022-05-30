package by.intexsoft.study.fileUtils.impl;

import by.intexsoft.study.fileUtils.CSVWriter;

import java.io.*;
import java.util.List;

public class CSVWriterImpl implements CSVWriter {

    private String filename;

    public CSVWriterImpl(String filename) {
        this.filename = filename;
    }

    @Override
    public void writeCSV(List<String> list) throws IOException {
       try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
           for (int i = 0; i < list.size(); i++){
               bw.write(list.get(i));
           }
       } catch (IOException e){
           e.printStackTrace();
       }
    }

}
