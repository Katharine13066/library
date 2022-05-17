package by.intexsoft.study;

import by.intexsoft.study.fileUtils.*;
import by.intexsoft.study.fileUtils.impl.*;
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
import by.intexsoft.study.storage.impl.JSONAuthorStorageWorkerImpl;
import by.intexsoft.study.storage.impl.JSONBookStorageWorkerImpl;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;


public class Starter {

    public static void main (String [] args) throws IOException, ParseException {

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

        System.out.println("Find book by id 4");
        System.out.println(bookStorageWorker.findBookById("4"));
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

        System.out.println("Find author by id 3");
        System.out.println(authorStorageWorker.findAuthorById("3"));

        JSONBookReader jsonBookReader = new JSONBookReaderImpl("book.json");
        JSONBookWriter jsonBookWriter = new JSONBookWriterImpl("book.json");
        BookStorageWorker jsonBookStorageWorker = new JSONBookStorageWorkerImpl(jsonBookReader, jsonBookWriter);

        List<Book> bookList = jsonBookReader.readJSON();

        System.out.println("Original book.json file");
        bookPrinter.printBooks(bookList);
        System.out.println("\n");

        System.out.println("Add new book");
        jsonBookStorageWorker.createBook(new Book("BookName", "4", "Publisher", "2000"));
        bookPrinter.printBooks(bookList);
        System.out.println("\n");

        System.out.println("Update 1 book");
        jsonBookStorageWorker.updateBook(new Book("1", "name", "2", "ls", "2000"));
        bookPrinter.printBooks(jsonBookStorageWorker.getAllBooks());
        System.out.println("\n");

        System.out.println("Delete 2 book");
        jsonBookStorageWorker.deleteBookById("2");
        bookPrinter.printBooks(jsonBookStorageWorker.getAllBooks());
        System.out.println("\n");

        System.out.println("Find book by id 4");
        System.out.println(jsonBookStorageWorker.findBookById("4"));
        System.out.println("\n");



        JSONAuthorReader jsonAuthorReader = new JSONAuthorReaderImpl("author.json");
        JSONAuthorWriter jsonAuthorWriter = new JSONAuthorWriterImpl("author.json");
        AuthorStorageWorker jsonAuthorStorageWorker = new JSONAuthorStorageWorkerImpl(jsonAuthorReader, jsonAuthorWriter);

        System.out.println("Original author.csv file");
        authorPrinter.printAuthors(jsonAuthorStorageWorker.getAllAuthor());
        System.out.println("\n");

        System.out.println("Add new author");
        jsonAuthorStorageWorker.createAuthor(new Author("Name", "phone", "email"));
        authorPrinter.printAuthors(jsonAuthorStorageWorker.getAllAuthor());
        System.out.println("\n");

        System.out.println("Update 1 author");
        jsonAuthorStorageWorker.updateAuthor(new Author("1", "name", null, null));
        authorPrinter.printAuthors(jsonAuthorStorageWorker.getAllAuthor());
        System.out.println("\n");

        System.out.println("Delete 2 author");
        jsonAuthorStorageWorker.deleteAuthorById("2");
        authorPrinter.printAuthors(jsonAuthorStorageWorker.getAllAuthor());
        System.out.println("\n");

        System.out.println("Find author by id 7");
        System.out.println(jsonAuthorStorageWorker.findAuthorById("7"));
    }
}

