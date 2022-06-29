package by.intexsoft.study.controllers;

import by.intexsoft.study.api.FeedbackApi;
import by.intexsoft.study.model.FeedbackDto;
import by.intexsoft.study.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeedbackController implements FeedbackApi {

    private FeedbackService feedbackService;

    @Autowired
    public FeedbackController(@Qualifier("feedbackService") FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @Override
    public ResponseEntity<Void> createFeedback(FeedbackDto feedbackDto) {
        feedbackService.create(feedbackDto);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Void> deleteAllFeedbacks() {
        feedbackService.deleteAll();
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Void> deleteFeedbackById(Long id) {
        feedbackService.deleteById(id);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<List<FeedbackDto>> findAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.findAll());
    }

    @Override
    public ResponseEntity<FeedbackDto> findByIdFeedback(Long id) {
        return ResponseEntity.ok(feedbackService.findById(id));
    }

    @Override
    public ResponseEntity<Void> patchFeedback(FeedbackDto feedbackDto) {
        feedbackService.patch(feedbackDto);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Void> updateFeedback(FeedbackDto feedbackDto) {
        feedbackService.update(feedbackDto);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @RequestMapping(value = "/book_feedback/{bookId}", method = RequestMethod.GET)
    public List<FeedbackDto> getFeedbacksByBookId(@PathVariable Long bookId){
         return feedbackService.getFeedbacksByBookId(bookId);
    }
}
