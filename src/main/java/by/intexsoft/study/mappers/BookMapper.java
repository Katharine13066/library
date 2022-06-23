package by.intexsoft.study.mappers;

import by.intexsoft.study.daomodel.Book;
import by.intexsoft.study.model.BookDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = AuthorMapper.class)
public interface BookMapper {

    BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

    @Mapping(source = "authors", target = "authorDTOList")
    BookDTO toDTO(Book book);

    List<BookDTO> toDTOs(List<Book> authors);

    @InheritInverseConfiguration
    Book fromDTO(BookDTO bookDTO);

    @InheritConfiguration
    @Mapping(target = "id", ignore = true)
    void updateBookrFromDto(BookDTO bookDTO, @MappingTarget Book book);
}
