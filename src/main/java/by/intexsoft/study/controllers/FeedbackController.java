package by.intexsoft.study.controllers;

import by.intexsoft.study.api.FeedbackApi;
import by.intexsoft.study.model.FeedbackDTO;
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
    public ResponseEntity<Void> createFeedback(FeedbackDTO feedbackDTO) {
        feedbackService.create(feedbackDTO);
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
    public ResponseEntity<List<FeedbackDTO>> findAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.findAll());
    }

    @Override
    public ResponseEntity<FeedbackDTO> findByIdFeedback(Long id) {
        return ResponseEntity.ok(feedbackService.findById(id));
    }

    @Override
    public ResponseEntity<Void> patchFeedback(FeedbackDTO feedbackDTO) {
        feedbackService.patch(feedbackDTO);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Void> updateFeedback(FeedbackDTO feedbackDTO) {
        feedbackService.update(feedbackDTO);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @RequestMapping(value = "/book_feedback/{book_id}", method = RequestMethod.GET)
    public List<FeedbackDTO> getFeedbacksByBookId(@PathVariable Long book_id){
         return feedbackService.getFeedbacksByBookId(book_id);
    }
}
