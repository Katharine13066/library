package by.intexsoft.study.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "BookHistory Entity")
public class BookHistoryDTO {

    @Schema(description = "ID")
    private Long id;
    @Schema(description = "User ID")
    private Long userID;
    @Schema(description = "Book ID")
    private Long bookID;
    @Schema(description = "Start Date")
    private String startDate;
    @Schema(description = "Return Date")
    private String returnDate;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public BookDTO getBooks() {
        return books;
    }

    public void setBooks(BookDTO books) {
        this.books = books;
    }

}
