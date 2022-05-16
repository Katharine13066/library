package by.intexsoft.study.fileUtils;

import java.io.IOException;
import java.util.List;

public interface CSVWriter {
    void writeCSV(List<String> list) throws IOException;
}

