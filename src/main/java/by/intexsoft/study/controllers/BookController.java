package by.intexsoft.study.controllers;

import by.intexsoft.study.api.BooksApi;

import by.intexsoft.study.model.BookDTO;
import by.intexsoft.study.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController implements BooksApi {

    private LibraryService<BookDTO> bookService;

    @Autowired
    public BookController(@Qualifier("bookService") LibraryService<BookDTO> bookService) {
        this.bookService = bookService;
    }

    @Override
    public ResponseEntity<Void> createBook(BookDTO bookDTO) {
        bookService.create(bookDTO);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Void> deleteAllBooks() {
        bookService.deleteAll();
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Void> deleteBookById(Long id) {
        bookService.deleteById(id);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<List<BookDTO>> findAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @Override
    public ResponseEntity<BookDTO> findByIdBook(Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @Override
    public ResponseEntity<Void> patchBook(BookDTO bookDTO) {
        bookService.patch(bookDTO);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }


    @Override
    public ResponseEntity<Void> updateBook(BookDTO bookDTO) {
        bookService.update(bookDTO);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }
}
