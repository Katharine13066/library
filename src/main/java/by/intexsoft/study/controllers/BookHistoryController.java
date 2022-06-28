package by.intexsoft.study.controllers;

import by.intexsoft.study.api.BookhistoryApi;
import by.intexsoft.study.model.BookHistoryDTO;
import by.intexsoft.study.service.BookHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class BookHistoryController implements BookhistoryApi {

    private BookHistoryService bookHistoryService;

    @Autowired
    public BookHistoryController(@Qualifier("bookhistoryService") BookHistoryService bookHistoryService) {
        this.bookHistoryService = bookHistoryService;
    }


    @Override
    public ResponseEntity<Void> createBookHistory(BookHistoryDTO bookHistoryDTO) {
        bookHistoryService.create(bookHistoryDTO);
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
    public ResponseEntity<List<BookHistoryDTO>> findAllBookHistory() {
        return ResponseEntity.ok(bookHistoryService.findAll());
    }

    @Override
    public ResponseEntity<BookHistoryDTO> findByIdBookHistory(Long id) {
        return ResponseEntity.ok(bookHistoryService.findById(id));
    }

    @Override
    public ResponseEntity<Void> patchBookHistory(BookHistoryDTO bookHistoryDTO) {
        bookHistoryService.patch(bookHistoryDTO);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Void> updateBookHistory(BookHistoryDTO bookHistoryDTO) {
        bookHistoryService.update(bookHistoryDTO);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @RequestMapping(value = "/take_book/{book_id}/{user_id}", method = RequestMethod.POST)
    public void takeBook(@PathVariable Long book_id, @PathVariable Long user_id){
        BookHistoryDTO bookHistory = new BookHistoryDTO();
        bookHistory.setBookID(book_id);
        bookHistory.setUserID(user_id);
        Date curDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-Mon-yyyy");
        bookHistory.setStartDate(simpleDateFormat.format(curDate));
        createBookHistory(bookHistory);
    }

    @RequestMapping(value = "/return_book/{book_id}/{user_id}", method = RequestMethod.PUT)
    public void returnBook(@PathVariable Long book_id, @PathVariable Long user_id){
        BookHistoryDTO bookHistory = bookHistoryService.findBookHistoryByBookAndUserIds(book_id, user_id);
        Date curDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-Mon-yyyy");
        bookHistory.setReturnDate(simpleDateFormat.format(curDate));
        updateBookHistory(bookHistory);
    }
}
