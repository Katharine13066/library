package by.intexsoft.study.mappers;

import by.intexsoft.study.daomodel.BookHistory;
import by.intexsoft.study.model.BookHistoryDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = BookMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookHistoryMapper {

    BookHistoryMapper bookHistoryMapper = Mappers.getMapper(BookHistoryMapper.class);

    BookHistoryDto toDto(BookHistory bookHistory);

    @InheritInverseConfiguration
    BookHistory fromDto(BookHistoryDto bookHistoryDto);

    List<BookHistoryDto> toDtos(List<BookHistory> bookHistories);

    @InheritConfiguration
    @Mapping(target = "id", ignore = true)
    void updateFeedbackFromDto(BookHistoryDto bookHistoryDto, @MappingTarget BookHistory bookHistory);

}
