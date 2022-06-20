package by.intexsoft.study.daomodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "birthday", nullable = false)
    private String birthday;

    @Column(name = "password_hash",unique = true, nullable = false)
    private Long passwordHash;

    @OneToMany(/*mappedBy="users"*/)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<BookHistory> bookHistoryList;

    @OneToMany(/*mappedBy="users"*/)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Feedback> feedbacks;

    public User(){}

    public User(String userName, String phoneNumber, String email, String birthday, Long passwordHash) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthday = birthday;
        this.passwordHash = passwordHash;
    }

    public User(Long id, String userName, String phoneNumber, String email, String birthday, Long passwordHash, List<BookHistory> bookHistoryList, List<Feedback> feedbacks) {
        this.id = id;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthday = birthday;
        this.passwordHash = passwordHash;
        this.bookHistoryList = bookHistoryList;
        this.feedbacks = feedbacks;
    }

    public void addFeedback(Feedback feedback){
        feedback.setUsers(this);
        feedbacks.add(feedback);
    }

    public void removeFeedback(Feedback feedback){
        feedbacks.remove(feedback);
    }

    public void addBookHistory(BookHistory bookHistory){
        bookHistory.setUsers(this);
        bookHistoryList.add(bookHistory);
    }

    public void removeBookHistory(BookHistory bookHistory){
        bookHistoryList.remove(bookHistory);
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Long getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(Long passwordHash) {
        this.passwordHash = passwordHash;
    }
}


