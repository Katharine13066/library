package by.intexsoft.study.controllers;

import by.intexsoft.study.api.BooksApi;
import by.intexsoft.study.model.BookDto;
import by.intexsoft.study.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController implements BooksApi {

   private BookService bookService;

    @Autowired
    public BookController(@Qualifier("bookService") BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public ResponseEntity<Void> createBook(BookDto bookDto) {
        bookService.create(bookDto);
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
    public ResponseEntity<List<BookDto>> findAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @Override
    public ResponseEntity<BookDto> findByIdBook(Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @Override
    public ResponseEntity<Void> patchBook(BookDto bookDto) {
        bookService.patch(bookDto);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Void> updateBook(BookDto bookDto) {
        bookService.update(bookDto);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @RequestMapping(value = "/take_book/{bookId}/{userId}", method = RequestMethod.PUT)
    public void takeBook(@PathVariable Long  bookId, @PathVariable Long userId){
        bookService.takeBook(bookId, userId);
    }

    @RequestMapping(value = "/return_book/{bookId}/{userId}", method = RequestMethod.PUT)
    public void returnBook(@PathVariable Long  bookId, @PathVariable Long userId){
        bookService.returnBook(bookId, userId);
    }

}
