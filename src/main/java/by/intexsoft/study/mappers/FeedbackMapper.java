package by.intexsoft.study.mappers;

import by.intexsoft.study.daomodel.Feedback;
import by.intexsoft.study.model.FeedbackDTO;
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
public interface FeedbackMapper {

    FeedbackMapper feedbackMapper = Mappers.getMapper(FeedbackMapper.class);

    FeedbackDTO toDTO(Feedback feedback);
    @InheritInverseConfiguration

    Feedback fromDTO(FeedbackDTO feedbackDTO);
    List<FeedbackDTO> toDTOs(List<Feedback> feedbacks);

    @InheritConfiguration
    @Mapping(target = "id", ignore = true)
    void updateFeedbackFromDto(FeedbackDTO feedbackDTO, @MappingTarget Feedback feedback);
}
