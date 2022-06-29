package by.intexsoft.study.model;

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
    private Long bookId;
    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    private Long userId;
    @Column(name = "start_date", nullable = false)
    private String startDate;
    @Column(name = "return_date", nullable = false)
    private String returnDate;
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public BookHistory(Long id, Long bookId, Long userId, String startDate, String returnDate, Book book, User user) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.book = book;
        this.user = user;
    }

    public BookHistory(Long bookId, Long userId, String startDate, String returnDate, Book book, User user) {
        this.bookId = bookId;
        this.userId = userId;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.book = book;
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book books) {
        this.book = books;
    }

    public BookHistory(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookID) {
        this.bookId = bookID;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userID) {
        this.userId = userID;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}