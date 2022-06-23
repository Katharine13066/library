package by.intexsoft.study.controllers;

import by.intexsoft.study.api.AuthorsApi;
import by.intexsoft.study.model.AuthorDTO;
import by.intexsoft.study.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController implements AuthorsApi {

    private LibraryService<AuthorDTO> authorService;

    @Autowired
    public AuthorController(@Qualifier("authorService")LibraryService<AuthorDTO> authorService) {
        this.authorService = authorService;
    }

    @Override
    public ResponseEntity<Void> createAuthor(AuthorDTO authorDTO) {
        authorService.create(authorDTO);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Void> deleteAllAuthors() {
        authorService.deleteAll();
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Void> deleteAuthorById(Long id) {
        authorService.deleteById(id);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<List<AuthorDTO>> findAllAuthors() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @Override
    public ResponseEntity<AuthorDTO> findByIdAuthor(Long id) {
        return ResponseEntity.ok(authorService.findById(id));
    }

    @Override
    public ResponseEntity<Void> patchAuthor(AuthorDTO authorDTO) {
        authorService.patch(authorDTO);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Void> updateAuthor(AuthorDTO authorDTO) {
        authorService.update(authorDTO);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

}
