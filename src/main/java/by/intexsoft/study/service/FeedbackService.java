package by.intexsoft.study.service;

import by.intexsoft.study.model.FeedbackDto;

import java.util.List;

public interface FeedbackService extends LibraryService<FeedbackDto>{

    List<FeedbackDto> getFeedbacksByBookId(Long bookId);
}
