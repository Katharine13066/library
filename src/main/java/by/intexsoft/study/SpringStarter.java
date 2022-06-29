package by.intexsoft.study;

import by.intexsoft.study.filters.Filter;
import by.intexsoft.study.model.Author;
import by.intexsoft.study.model.Book;
import by.intexsoft.study.orders.Order;
import by.intexsoft.study.printer.AuthorPrinter;
import by.intexsoft.study.printer.BookPrinter;
import by.intexsoft.study.storage.AuthorStorageWorker;
import by.intexsoft.study.storage.BookStorageWorker;
import by.intexsoft.study.storage.impl.JSONBookStorageWorkerImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static by.intexsoft.study.filters.Operator.BIGGERTHAN;
import static by.intexsoft.study.filters.Operator.CONTAINS;
import static by.intexsoft.study.filters.Operator.EQUALS;
import static by.intexsoft.study.filters.Operator.SMALLERTHAN;
import static by.intexsoft.study.filters.Operator.STARTSWITH;
import static by.intexsoft.study.orders.OrderTypes.ASC;
import static by.intexsoft.study.orders.OrderTypes.DESC;

public class SpringStarter {

    public static void main (String [] args) throws IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        BookPrinter bookPrinter = context.getBean(BookPrinter.class);
        BookStorageWorker bookStorageWorker = context.getBean("bookStorageWorkerImpl", BookStorageWorker.class);

        System.out.println("Original book.csv file");
        bookPrinter.printBooks(bookStorageWorker.getAllBooks());
        System.out.println("\n");

        List<Filter> bookFilters = new ArrayList<>();
        bookFilters.add(new Filter<String>("bookName", "Th", STARTSWITH));
        bookFilters.add(new Filter<String>("publicationDate", "20", CONTAINS));

        List<Order> bookOrders = new ArrayList<>();
        bookOrders.add(new Order("authorID", DESC));

        System.out.println("Filters");
        bookPrinter.printBooks(bookStorageWorker.getAllBooks(bookFilters));
        System.out.println("\n");

        System.out.println("Order");
        bookPrinter.printBooks(bookStorageWorker.orderAllBooks(bookOrders));
        System.out.println("\n");

        System.out.println("Filter and Order");
        bookPrinter.printBooks(bookStorageWorker.getAllBooks(bookFilters, bookOrders));
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

        System.out.println("Find book by author id 5");
        System.out.println(bookStorageWorker.findBookByAuthorId("5"));
        System.out.println("\n");

        List<Filter> authorFilters = new ArrayList<>();
        authorFilters.add(new Filter<Integer>("age", 30, BIGGERTHAN));
        authorFilters.add(new Filter<Integer>("age", 61, SMALLERTHAN));

        List<Order> authorOrders = new ArrayList<>();
        authorOrders.add(new Order("age", DESC));

        AuthorPrinter authorPrinter = context.getBean(AuthorPrinter.class);
        AuthorStorageWorker authorStorageWorker = context.getBean("authorStorageWorkerImpl", AuthorStorageWorker.class);

        System.out.println("Original author.csv file");
        authorPrinter.printAuthors(authorStorageWorker.getAllAuthor());
        System.out.println("\n");


        System.out.println("Filters");
        authorPrinter.printAuthors(authorStorageWorker.getAllAuthor(authorFilters));
        System.out.println("\n");

        System.out.println("Order");
        authorPrinter.printAuthors(authorStorageWorker.orderAllAuthor(authorOrders));
        System.out.println("\n");

        System.out.println("Filter and Order");
        authorPrinter.printAuthors(authorStorageWorker.getAllAuthor(authorFilters, authorOrders));
        System.out.println("\n");


        System.out.println("Add new author");
        authorStorageWorker.createAuthor(new Author("Name", "phone", "email", 20));
        authorPrinter.printAuthors(authorStorageWorker.getAllAuthor());
        System.out.println("\n");

        System.out.println("Update 2 author");
        authorStorageWorker.updateAuthor(new Author("2", "name", null, null, 21));
        authorPrinter.printAuthors(authorStorageWorker.getAllAuthor());
        System.out.println("\n");

        System.out.println("Delete first author");
        authorStorageWorker.deleteAuthorById("1");
        authorPrinter.printAuthors(authorStorageWorker.getAllAuthor());
        System.out.println("\n");

        System.out.println("Find author by id 3");
        System.out.println(authorStorageWorker.findAuthorById("3"));
        System.out.println("\n");

        BookStorageWorker jsonBookStorageWorker = context.getBean(JSONBookStorageWorkerImpl.class);

        System.out.println("Original book.json file");
        bookPrinter.printBooks(jsonBookStorageWorker.getAllBooks());
        System.out.println("\n");

        System.out.println("Filters");
        bookPrinter.printBooks(jsonBookStorageWorker.getAllBooks(bookFilters));
        System.out.println("\n");

        List<Order> bookJsonOrders = new ArrayList<>();
        bookJsonOrders.add(new Order("authorID", ASC));

        System.out.println("Order");
        bookPrinter.printBooks(jsonBookStorageWorker.orderAllBooks(bookJsonOrders));
        System.out.println("\n");

        System.out.println("Filters and Order");
        bookPrinter.printBooks(jsonBookStorageWorker.getAllBooks(bookFilters, bookJsonOrders));
        System.out.println("\n");

        System.out.println("Add new book");
        jsonBookStorageWorker.createBook(new Book("BookName", "4", "Publisher", "2000"));
        bookPrinter.printBooks(jsonBookStorageWorker.getAllBooks());
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

       AuthorStorageWorker jsonAuthorStorageWorker = context.getBean("jsonAuthorStorageWorkerImpl",AuthorStorageWorker.class);

        System.out.println("Original author.json file");
        authorPrinter.printAuthors(jsonAuthorStorageWorker.getAllAuthor());
        System.out.println("\n");

        List<Filter> jsonAuthorFilters = new ArrayList<>();
        jsonAuthorFilters.add(new Filter<Integer>("age", 33, EQUALS));

        System.out.println("Filters");
        authorPrinter.printAuthors(jsonAuthorStorageWorker.getAllAuthor(jsonAuthorFilters));
        System.out.println("\n");

        authorOrders.add(new Order("authorID", ASC));

        System.out.println("Order");
        authorPrinter.printAuthors(jsonAuthorStorageWorker.orderAllAuthor(authorOrders));
        System.out.println("\n");

        System.out.println("Filter and Order");
        authorPrinter.printAuthors(jsonAuthorStorageWorker.getAllAuthor(jsonAuthorFilters, authorOrders));
        System.out.println("\n");


        System.out.println("Add new author");
        jsonAuthorStorageWorker.createAuthor(new Author("Name", "phone", "email", 20));
        authorPrinter.printAuthors(jsonAuthorStorageWorker.getAllAuthor());
        System.out.println("\n");

        System.out.println("Update 3 author");
        jsonAuthorStorageWorker.updateAuthor(new Author("3", "name", null, null, 45));
        authorPrinter.printAuthors(jsonAuthorStorageWorker.getAllAuthor());
        System.out.println("\n");

        System.out.println("Delete 2 author");
        jsonAuthorStorageWorker.deleteAuthorById("2");
        authorPrinter.printAuthors(jsonAuthorStorageWorker.getAllAuthor());
        System.out.println("\n");

        System.out.println("Find author by id 7");
        System.out.println(jsonAuthorStorageWorker.findAuthorById("7"));
        System.out.println("\n");

    }
}
