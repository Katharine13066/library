package by.intexsoft.study.service;

import by.intexsoft.study.model.FeedbackDTO;

import java.util.List;

public interface FeedbackService extends LibraryService<FeedbackDTO>{

    List<FeedbackDTO> getFeedbacksByBookId(Long book_id);
}
