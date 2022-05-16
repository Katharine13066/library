package by.intexsoft.study;

import by.intexsoft.study.fileUtils.CSVReader;
import by.intexsoft.study.fileUtils.CSVWriter;
import by.intexsoft.study.fileUtils.impl.CSVReaderImpl;
import by.intexsoft.study.fileUtils.impl.CSVWriterImpl;
import by.intexsoft.study.model.Book;
import by.intexsoft.study.parser.BookParser;
import by.intexsoft.study.parser.impl.CSVBookParserImpl;
import by.intexsoft.study.printer.Printer;
import by.intexsoft.study.printer.impl.PrinterImpl;
import by.intexsoft.study.storage.StorageWorker;
import by.intexsoft.study.storage.impl.StorageWorkerImpl;

import java.io.IOException;
import java.util.List;

import static java.lang.System.out;

public class Starter {

    public static void main (String [] args) throws IOException {

        BookParser bookParser = new CSVBookParserImpl();
        CSVReader reader = new CSVReaderImpl("book.csv");
        CSVWriter writer = new CSVWriterImpl("book.csv");
        StorageWorker storageWorker = new StorageWorkerImpl(reader, writer, bookParser);
        Printer printer = new PrinterImpl();

        out.println("Original file");
        printer.printBooks(storageWorker.getAllBooks());
        out.println("\n");

        out.println("Add new book");
        storageWorker.createBook(new Book("BookName", "Author", "Publisher", "2000"));
        printer.printBooks(storageWorker.getAllBooks());
        out.println("\n");

        out.println("Update 1 book");
        storageWorker.updateBook(new Book("1", "name", "kate", "ls", "2000"));
        printer.printBooks(storageWorker.getAllBooks());
        out.println("\n");

        out.println("Delete second book");
        storageWorker.deleteBookById("2");
        printer.printBooks(storageWorker.getAllBooks());
        out.println("\n");

    }
}
