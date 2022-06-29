package by.intexsoft.study.controllers;

import by.intexsoft.study.api.BookhistoryApi;
import by.intexsoft.study.model.AuthorDto;
import by.intexsoft.study.model.BookDto;
import by.intexsoft.study.model.BookHistoryDto;
import by.intexsoft.study.service.BookHistoryService;
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
public class BookHistoryController implements BookhistoryApi {

    private BookHistoryService bookHistoryService;

    @Autowired
    public BookHistoryController(@Qualifier("bookhistoryService") BookHistoryService bookHistoryService) {
        this.bookHistoryService = bookHistoryService;
    }

    @Override
    public ResponseEntity<Void> createBookHistory(BookHistoryDto bookHistoryDto) {
        bookHistoryService.create(bookHistoryDto);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Void> deleteAllBookHistory() {
        bookHistoryService.deleteAll();
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Void> deleteBookHistoryById(Long id) {
        bookHistoryService.deleteById(id);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<List<BookHistoryDto>> findAllBookHistory() {
        return ResponseEntity.ok(bookHistoryService.findAll());
    }

    @Override
    public ResponseEntity<BookHistoryDto> findByIdBookHistory(Long id) {
        return ResponseEntity.ok(bookHistoryService.findById(id));
    }

    @Override
    public ResponseEntity<Void> patchBookHistory(BookHistoryDto bookHistoryDto) {
        bookHistoryService.patch(bookHistoryDto);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Void> updateBookHistory(BookHistoryDto bookHistoryDto) {
        bookHistoryService.update(bookHistoryDto);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @RequestMapping(value = "/book_history/{bookId}", method = RequestMethod.GET)
    public List<BookHistoryDto> getBookHistoryByBookId(@PathVariable Long bookId){
        return bookHistoryService.findBookHistoryByBookId(bookId);
    }

    @RequestMapping(value = "/top10books", method = RequestMethod.GET)
    public List<BookDto> get10TheMostPopularBooks(){
        return bookHistoryService.get10TheMostPopularBooks();
    }

    @RequestMapping(value = "/top10authors", method = RequestMethod.GET)
    public List<AuthorDto> get10TheMostPopularAuthors() {
        return bookHistoryService.get10TheMostPopularAuthors();
    }

}
