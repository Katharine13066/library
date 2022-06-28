package by.intexsoft.study.mappers;

import by.intexsoft.study.daomodel.User;
import by.intexsoft.study.model.UserDTO;
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
public interface UserMapper {

    UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    UserDTO toDTO(User user);
    @InheritInverseConfiguration

    User fromDTO(UserDTO userDTO);
    List<UserDTO> toDTOs(List<User> users);

    @InheritConfiguration
    @Mapping(target = "id", ignore = true)
    void updateUserFromDto(UserDTO userDTO, @MappingTarget User user);
}
