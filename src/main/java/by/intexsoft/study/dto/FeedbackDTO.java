package by.intexsoft.study.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Feedback entity")
public class FeedbackDTO {

    @Schema(description = "ID")
    private Long id;
    @Schema(description = "User ID")
    private Long userID;
    @Schema(description = "Book ID")
    private Long bookID;
    @Schema(description = "Comments")
    private String comments;
    @Schema(description = "Book")
    private BookDTO books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public BookDTO getBooks() {
        return books;
    }

    public void setBooks(BookDTO books) {
        this.books = books;
    }

}
