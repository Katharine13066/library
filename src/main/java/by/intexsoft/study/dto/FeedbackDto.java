package by.intexsoft.study.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Feedback entity")
public class FeedbackDto {
    @Schema(description = "Id")
    private Long id;
    @Schema(description = "User Id")
    private Long userId;
    @Schema(description = "Book Id")
    private Long bookId;
    @Schema(description = "Comments")
    private String comments;
    @Schema(description = "Book")
    private BookDto book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }
}
