package by.intexsoft.study.model;

public class Author {

    String authorID;

    String authorName;

    String phoneNumber;

    String email;

    public Author(){}

    public Author(String authorID, String authorName, String phoneNumber, String email) {
        this.authorID = authorID;
        this.authorName = authorName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Author(String authorName, String phoneNumber, String email) {
        this.authorName = authorName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorID='" + authorID + '\'' +
                ", authorName='" + authorName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
