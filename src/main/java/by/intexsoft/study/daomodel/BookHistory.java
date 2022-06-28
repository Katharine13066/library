package by.intexsoft.study.daomodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="bookhistory")
public class BookHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id", nullable = false, insertable = false, updatable = false)
    private Long bookID;

    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    private Long userID;

    @Column(name = "start_date", nullable = false)
    private String startDate;

    @Column(name = "return_date", nullable = false)
    private String returnDate;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book books;

    public BookHistory(Long id, Long bookID, Long userID, String startDate, String returnDate, Book books) {
        this.id = id;
        this.bookID = bookID;
        this.userID = userID;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.books = books;
    }

    public BookHistory(Long bookID, Long userID, String startDate, String returnDate, Book books) {
        this.bookID = bookID;
        this.userID = userID;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.books = books;
    }

    public Book getBooks() {
        return books;
    }

    public void setBooks(Book books) {
        this.books = books;
    }

    public BookHistory(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
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
}