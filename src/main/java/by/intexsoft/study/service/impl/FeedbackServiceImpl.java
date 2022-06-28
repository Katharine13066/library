package by.intexsoft.study.service.impl;

import by.intexsoft.study.daomodel.Feedback;
import by.intexsoft.study.mappers.FeedbackMapper;
import by.intexsoft.study.model.FeedbackDTO;
import by.intexsoft.study.repositories.FeedbackDAO;
import by.intexsoft.study.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackDAO feedbackDAO;

    @Autowired
    private FeedbackMapper feedbackMapper;


    public FeedbackServiceImpl(FeedbackDAO feedbackDAO, FeedbackMapper feedbackMapper) {
        this.feedbackDAO = feedbackDAO;
        this.feedbackMapper = feedbackMapper;
    }


    @Override
    public FeedbackDTO findById(Long id) {
        return feedbackMapper.toDTO(feedbackDAO.findById(id));
    }

    @Override
    public List<FeedbackDTO> findByIds(List<Long> list) {
        return feedbackMapper.toDTOs(feedbackDAO.findByIds(list));
    }

    @Override
    public List<FeedbackDTO> findAll() {
        List<Feedback> feedbacks = feedbackDAO.findAll();
        return feedbackMapper.toDTOs(feedbacks);
    }

    @Override
    @Transactional
    public void deleteAll() {
        feedbackDAO.deleteAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        feedbackDAO.deleteById(id);
    }

    @Override
    @Transactional
    public FeedbackDTO create(FeedbackDTO feedbackDTO) {
        return feedbackMapper.toDTO(feedbackDAO.createEntity(feedbackMapper.fromDTO(feedbackDTO)));
    }

    @Override
    @Transactional
    public FeedbackDTO update(FeedbackDTO feedbackDTO) {
        return feedbackMapper.toDTO(feedbackDAO.updateEntity(feedbackMapper.fromDTO(feedbackDTO)));
    }

    @Override
    @Transactional
    public void patch(FeedbackDTO feedbackDTO) {
        Feedback feedback = feedbackDAO.findById(feedbackDTO.getId());
        feedbackMapper.updateFeedbackFromDto(feedbackDTO, feedback);

    }
}
