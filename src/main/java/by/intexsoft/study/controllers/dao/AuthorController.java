package by.intexsoft.study.controllers.dao;

import by.intexsoft.study.dto.AuthorDTO;
import by.intexsoft.study.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private LibraryService<AuthorDTO> authorService;

    public AuthorController(LibraryService<AuthorDTO> authorService) {
        this.authorService = authorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<AuthorDTO> findAllAuthors() {
        return authorService.findAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public AuthorDTO findByIdAuthor(@PathVariable Long id) {
       return authorService.findById(id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id){
        authorService.deleteById(id);
    }

    @RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
    public void deleteAll(){
        authorService.deleteAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody AuthorDTO author){
        authorService.create(author);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody AuthorDTO author){
        authorService.update(author);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public void patch(@RequestBody AuthorDTO authorDTO){
        authorService.patch(authorDTO);
    }

}
