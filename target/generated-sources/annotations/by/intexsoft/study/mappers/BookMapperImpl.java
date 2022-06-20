package by.intexsoft.study.mappers;

import by.intexsoft.study.daomodel.Author;
import by.intexsoft.study.daomodel.Book;
import by.intexsoft.study.dto.AuthorDTO;
import by.intexsoft.study.dto.BookDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-20T13:45:47+0300",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public BookDTO toDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDTO bookDTO = new BookDTO();

        bookDTO.setAuthorDTOList( authorMapper.toDTOs( book.getAuthors() ) );
        bookDTO.setId( book.getId() );
        bookDTO.setBookName( book.getBookName() );
        bookDTO.setPublisher( book.getPublisher() );
        bookDTO.setPublicationDate( book.getPublicationDate() );
        bookDTO.setStatus( book.getStatus() );

        return bookDTO;
    }

    @Override
    public List<BookDTO> toDTOs(List<Book> authors) {
        if ( authors == null ) {
            return null;
        }

        List<BookDTO> list = new ArrayList<BookDTO>( authors.size() );
        for ( Book book : authors ) {
            list.add( toDTO( book ) );
        }

        return list;
    }

    @Override
    public Book fromDTO(BookDTO bookDTO) {
        if ( bookDTO == null ) {
            return null;
        }

        Book book = new Book();

        book.setAuthors( authorDTOListToAuthorList( bookDTO.getAuthorDTOList() ) );
        book.setId( bookDTO.getId() );
        book.setBookName( bookDTO.getBookName() );
        book.setPublisher( bookDTO.getPublisher() );
        book.setPublicationDate( bookDTO.getPublicationDate() );
        book.setStatus( bookDTO.getStatus() );

        return book;
    }

    @Override
    public void updateBookrFromDto(BookDTO bookDTO, Book book) {
        if ( bookDTO == null ) {
            return;
        }

        if ( book.getAuthors() != null ) {
            List<Author> list = authorDTOListToAuthorList( bookDTO.getAuthorDTOList() );
            if ( list != null ) {
                book.getAuthors().clear();
                book.getAuthors().addAll( list );
            }
            else {
                book.setAuthors( null );
            }
        }
        else {
            List<Author> list = authorDTOListToAuthorList( bookDTO.getAuthorDTOList() );
            if ( list != null ) {
                book.setAuthors( list );
            }
        }
        book.setBookName( bookDTO.getBookName() );
        book.setPublisher( bookDTO.getPublisher() );
        book.setPublicationDate( bookDTO.getPublicationDate() );
        book.setStatus( bookDTO.getStatus() );
    }

    protected List<Author> authorDTOListToAuthorList(List<AuthorDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Author> list1 = new ArrayList<Author>( list.size() );
        for ( AuthorDTO authorDTO : list ) {
            list1.add( authorMapper.fromDTO( authorDTO ) );
        }

        return list1;
    }
}
