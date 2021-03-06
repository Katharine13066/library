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
@Table(name = "feedback")
public class Feedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    private Long userId;
    @Column(name = "book_id", nullable = false, insertable = false, updatable = false)
    private Long bookId;
    @Column(name = "comments", nullable = false)
    private String comments;
    @ManyToOne()
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    public Feedback(){}

    public Feedback(Long id, Long userId, Long bookId, String comments, Book book) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.comments = comments;
        this.book = book;
    }

    public Feedback(Long userId, Long bookId, String comments, Book book) {
        this.userId = userId;
        this.bookId = bookId;
        this.comments = comments;
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book books) {
        this.book = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userID) {
        this.userId = userID;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookID) {
        this.bookId = bookID;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
