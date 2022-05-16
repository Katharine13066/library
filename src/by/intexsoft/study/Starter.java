package by.intexsoft.study;

import by.intexsoft.study.fileUtils.CSVReader;
import by.intexsoft.study.fileUtils.CSVWriter;
import by.intexsoft.study.fileUtils.impl.CSVReaderImpl;
import by.intexsoft.study.fileUtils.impl.CSVWriterImpl;
import by.intexsoft.study.model.Author;
import by.intexsoft.study.model.Book;
import by.intexsoft.study.parser.AuthorParser;
import by.intexsoft.study.parser.BookParser;
import by.intexsoft.study.parser.impl.CSVBookParserImpl;
import by.intexsoft.study.parser.impl.CVSAuthorParserImpl;
import by.intexsoft.study.printer.AuthorPrinter;
import by.intexsoft.study.printer.BookPrinter;
import by.intexsoft.study.printer.impl.AuthorPrinterImpl;
import by.intexsoft.study.printer.impl.BookPrinterImpl;
import by.intexsoft.study.storage.AuthorStorageWorker;
import by.intexsoft.study.storage.BookStorageWorker;
import by.intexsoft.study.storage.impl.AuthorStorageWorkerImpl;
import by.intexsoft.study.storage.impl.BookStorageWorkerImpl;

import java.io.IOException;

import static java.lang.System.out;

public class Starter {

    public static void main (String [] args) throws IOException {

        BookParser bookParser = new CSVBookParserImpl();
        CSVReader bookReader = new CSVReaderImpl("book.csv");
        CSVWriter bookWriter = new CSVWriterImpl("book.csv");
        BookStorageWorker bookStorageWorker = new BookStorageWorkerImpl(bookReader, bookWriter, bookParser);

        BookPrinter bookPrinter = new BookPrinterImpl();

        System.out.println("Original book.csv file");
        bookPrinter.printBooks(bookStorageWorker.getAllBooks());
        System.out.println("\n");

        System.out.println("Add new book");
        bookStorageWorker.createBook(new Book("BookName", "4", "Publisher", "2000"));
        bookPrinter.printBooks(bookStorageWorker.getAllBooks());
        System.out.println("\n");

        System.out.println("Update 1 book");
        bookStorageWorker.updateBook(new Book("1", "name", "2", "ls", "2000"));
        bookPrinter.printBooks(bookStorageWorker.getAllBooks());
        System.out.println("\n");

        System.out.println("Delete second book");
        bookStorageWorker.deleteBookById("2");
        bookPrinter.printBooks(bookStorageWorker.getAllBooks());
        System.out.println("\n");

        AuthorParser authorParser = new CVSAuthorParserImpl();
        CSVReader authorReader = new CSVReaderImpl("author.csv");
        CSVWriter authorWriter = new CSVWriterImpl("author.csv");
        AuthorStorageWorker authorStorageWorker = new AuthorStorageWorkerImpl(authorReader, authorWriter, authorParser);

        AuthorPrinter authorPrinter = new AuthorPrinterImpl();

        System.out.println("Original author.csv file");
        authorPrinter.printAuthors(authorStorageWorker.getAllAuthor());
        System.out.println("\n");

        System.out.println("Add new author");
        authorStorageWorker.createAuthor(new Author("Name", "phone", "email"));
        authorPrinter.printAuthors(authorStorageWorker.getAllAuthor());
        System.out.println("\n");

        System.out.println("Update 2 author");
        authorStorageWorker.updateAuthor(new Author("2", "name", null, null));
        authorPrinter.printAuthors(authorStorageWorker.getAllAuthor());
        System.out.println("\n");

        System.out.println("Delete first author");
        authorStorageWorker.deleteAuthorById("1");
        authorPrinter.printAuthors(authorStorageWorker.getAllAuthor());
        System.out.println("\n");

    }
}

