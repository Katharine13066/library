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
    private Long userID;

    @Column(name = "book_id", nullable = false, insertable = false, updatable = false)
    private Long bookID;

    @Column(name = "comments", nullable = false)
    private String comments;

    @ManyToOne()
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book books;

    public Feedback(){}

    public Feedback(Long id, Long userID, Long bookID, String comments, Book books) {
        this.id = id;
        this.userID = userID;
        this.bookID = bookID;
        this.comments = comments;
        this.books = books;
    }

    public Feedback(Long userID, Long bookID, String comments, Book books) {
        this.userID = userID;
        this.bookID = bookID;
        this.comments = comments;
        this.books = books;
    }

    public Book getBooks() {
        return books;
    }

    public void setBooks(Book books) {
        this.books = books;
    }

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
}
