package by.intexsoft.study.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Book Entity")
public class BookDto {
    @Schema(description = "Id")
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
    private List<AuthorDto> authorDtoList;
    @Schema(description = "BookHistory List")
    private List<BookHistoryDto> bookHistoryDtoList;
    @Schema(description = "Feedback List")
    private List<FeedbackDto> feedbackDtoList;

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

    public List<AuthorDto> getAuthorDTOList() {
        return authorDtoList;
    }

    public void setAuthorDTOList(List<AuthorDto> authorDtoList) {
        this.authorDtoList = authorDtoList;
    }

    public List<BookHistoryDto> getBookHistoryDtoList() {
        return bookHistoryDtoList;
    }

    public void setBookHistoryDtoList(List<BookHistoryDto> bookHistoryDtoList) {
        this.bookHistoryDtoList = bookHistoryDtoList;
    }

    public List<FeedbackDto> getFeedbackDtoList() {
        return feedbackDtoList;
    }

    public void setFeedbackDtoList(List<FeedbackDto> feedbackDtoList) {
        this.feedbackDtoList = feedbackDtoList;
    }
}
