package by.intexsoft.study.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "BookHistory Entity")
public class BookHistoryDto {
    @Schema(description = "Id")
    private Long id;
    @Schema(description = "User Id")
    private Long userId;
    @Schema(description = "Book Id")
    private Long bookId;
    @Schema(description = "Start Date")
    private String startDate;
    @Schema(description = "Return Date")
    private String returnDate;
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

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }
}
