package by.intexsoft.study.fileUtils.impl;

import by.intexsoft.study.fileUtils.CSVReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderImpl implements CSVReader {

    private String filename;

    public CSVReaderImpl(String filename) {
        this.filename = filename;
    }

    @Override
    public List<String> readCSV() {
        List<String> list = new ArrayList<>();
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((line = br.readLine()) != null)
            {
                list.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
