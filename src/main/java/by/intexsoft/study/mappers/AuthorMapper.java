package by.intexsoft.study.mappers;

import by.intexsoft.study.daomodel.Author;
import by.intexsoft.study.dto.AuthorDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthorMapper {

    AuthorMapper authorMapper = Mappers.getMapper(AuthorMapper.class);
    AuthorDTO toDTO(Author author);
    @InheritInverseConfiguration
    Author fromDTO(AuthorDTO authorDTO);
    List<AuthorDTO> toDTOs(List<Author> authors);


    @InheritConfiguration
    @Mapping(target = "id", ignore = true)
    void updateAuthorFromDto(AuthorDTO authorDTO, @MappingTarget Author author);

}
