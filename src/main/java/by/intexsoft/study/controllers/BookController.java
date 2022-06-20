package by.intexsoft.study.controllers;

import by.intexsoft.study.model.Book;
import by.intexsoft.study.storage.BookStorageWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

public class BookController {

    private BookStorageWorker jsonBookStorageWorker;

    @Autowired
    BookController(@Qualifier("jsonBookStorageWorkerImpl")BookStorageWorker jsonBookStorageWorker){
        this.jsonBookStorageWorker = jsonBookStorageWorker;
    }

    @GetMapping("/books")
    List<Book> getAllBooks() throws IOException {
        return jsonBookStorageWorker.getAllBooks();
    }
}
