package by.intexsoft.study.mappers;

import by.intexsoft.study.daomodel.Book;
import by.intexsoft.study.model.BookDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {AuthorMapper.class, BookHistoryMapper.class, FeedbackMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookMapper {

    BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

    BookDto toDto(Book book);

    List<BookDto> toDtos(List<Book> authors);

    @InheritInverseConfiguration
    Book fromDto(BookDto bookDto);

    @InheritConfiguration
    @Mapping(target = "id", ignore = true)
    void updateBookFromDto(BookDto bookDto, @MappingTarget Book book);

}
