package by.intexsoft.study.daomodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name= "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "book_name", nullable = false)
    private String bookName;
    @Column(name = "publisher", nullable = false)
    private String publisher;
    @Column(name = "publication_date", nullable = false)
    private String publicationDate;
    @Column(name="status", nullable = false)
    private Boolean status;

    @ManyToMany
    @JoinTable(name="book_author",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @OneToMany
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private List<BookHistory> bookHistoryList;

    @OneToMany
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private List<Feedback> feedbacks;

    public Book(){}

    public Book(Long id, String bookName, String publisher, String publicationDate, Boolean status, List<Author> authors, List<BookHistory> bookHistoryList, List<Feedback> feedbacks) {
        this.id = id;
        this.bookName = bookName;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.status = status;
        this.authors = authors;
        this.bookHistoryList = bookHistoryList;
        this.feedbacks = feedbacks;
    }

    public Book(String bookName, String publisher, String publicationDate, Boolean status, List<Author> authors, List<BookHistory> bookHistoryList, List<Feedback> feedbacks) {
        this.bookName = bookName;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.status = status;
        this.authors = authors;
        this.bookHistoryList = bookHistoryList;
        this.feedbacks = feedbacks;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

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

    public List<BookHistory> getBookHistoryList() {
        return bookHistoryList;
    }

    public void setBookHistoryList(List<BookHistory> bookHistoryList) {
        this.bookHistoryList = bookHistoryList;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
}