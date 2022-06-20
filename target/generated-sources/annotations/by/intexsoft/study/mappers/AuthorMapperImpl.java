package by.intexsoft.study.mappers;

import by.intexsoft.study.daomodel.Author;
import by.intexsoft.study.dto.AuthorDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-20T13:45:47+0300",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public AuthorDTO toDTO(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorDTO authorDTO = new AuthorDTO();

        authorDTO.setId( author.getId() );
        authorDTO.setAuthorName( author.getAuthorName() );
        authorDTO.setPhoneNumber( author.getPhoneNumber() );
        authorDTO.setEmail( author.getEmail() );
        authorDTO.setAge( author.getAge() );

        return authorDTO;
    }

    @Override
    public Author fromDTO(AuthorDTO authorDTO) {
        if ( authorDTO == null ) {
            return null;
        }

        Author author = new Author();

        author.setId( authorDTO.getId() );
        author.setAuthorName( authorDTO.getAuthorName() );
        author.setPhoneNumber( authorDTO.getPhoneNumber() );
        author.setEmail( authorDTO.getEmail() );
        author.setAge( authorDTO.getAge() );

        return author;
    }

    @Override
    public List<AuthorDTO> toDTOs(List<Author> authors) {
        if ( authors == null ) {
            return null;
        }

        List<AuthorDTO> list = new ArrayList<AuthorDTO>( authors.size() );
        for ( Author author : authors ) {
            list.add( toDTO( author ) );
        }

        return list;
    }

    @Override
    public void updateAuthorFromDto(AuthorDTO authorDTO, Author author) {
        if ( authorDTO == null ) {
            return;
        }

        if ( authorDTO.getAuthorName() != null ) {
            author.setAuthorName( authorDTO.getAuthorName() );
        }
        if ( authorDTO.getPhoneNumber() != null ) {
            author.setPhoneNumber( authorDTO.getPhoneNumber() );
        }
        if ( authorDTO.getEmail() != null ) {
            author.setEmail( authorDTO.getEmail() );
        }
        if ( authorDTO.getAge() != null ) {
            author.setAge( authorDTO.getAge() );
        }
    }
}
