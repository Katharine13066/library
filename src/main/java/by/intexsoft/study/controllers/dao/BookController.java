package by.intexsoft.study.controllers.dao;

import by.intexsoft.study.dto.BookDTO;
import by.intexsoft.study.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private LibraryService<BookDTO> bookService;

    public BookController(LibraryService<BookDTO> bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<BookDTO> findAllBooks() {
        return bookService.findAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public BookDTO findByIdBook(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id){
        bookService.deleteById(id);
    }

    @RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
    public void deleteAll(){
        bookService.deleteAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody BookDTO bookDTO){
        bookService.create(bookDTO);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody BookDTO bookDTO){
        bookService.update(bookDTO);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public void patch(@RequestBody BookDTO bookDTO){
        bookService.patch(bookDTO);
    }

}
