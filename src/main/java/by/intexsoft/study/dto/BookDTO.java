package by.intexsoft.study.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Book Entity")
public class BookDTO {

    @Schema(description = "ID")
    private Long id;
    @Schema(description = "Book name")
    private String bookName;
    @Schema(description = "Publisher")
    private String publisher;
    @Schema(description = "Publication date")
    private String publicationDate;
    @Schema(description = "Book status")
    private Boolean status;
    @Schema(description = "Author(s)")
    private List<AuthorDTO> authorDTOList;

    @Schema(description = "BookHistory List")
    private List<BookHistoryDTO> bookHistoryDTOList;

    @Schema(description = "Feedback List")
    private List<FeedbackDTO> feedbackDTOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<AuthorDTO> getAuthorDTOList() {
        return authorDTOList;
    }

    public void setAuthorDTOList(List<AuthorDTO> authorDTOList) {
        this.authorDTOList = authorDTOList;
    }

    public List<BookHistoryDTO> getBookHistoryDTOList() {
        return bookHistoryDTOList;
    }

    public void setBookHistoryDTOList(List<BookHistoryDTO> bookHistoryDTOList) {
        this.bookHistoryDTOList = bookHistoryDTOList;
    }

    public List<FeedbackDTO> getFeedbackDTOList() {
        return feedbackDTOList;
    }

    public void setFeedbackDTOList(List<FeedbackDTO> feedbackDTOList) {
        this.feedbackDTOList = feedbackDTOList;
    }
}
