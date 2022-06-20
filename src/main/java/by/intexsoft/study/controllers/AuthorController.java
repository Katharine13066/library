package by.intexsoft.study.controllers;

import by.intexsoft.study.model.Author;
import by.intexsoft.study.storage.AuthorStorageWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

public class AuthorController {

    private AuthorStorageWorker jsonAuthorStorageWorker;

    @Autowired
    AuthorController(@Qualifier("jsonAuthorStorageWorkerImpl")AuthorStorageWorker jsonAuthorStorageWorker){
        this.jsonAuthorStorageWorker = jsonAuthorStorageWorker;
    }

    @GetMapping("/authors")
    List<Author> getAllAuthors() throws IOException {
        return jsonAuthorStorageWorker.getAllAuthor();
    }
}
