package by.intexsoft.study.model;

public class Author {

    String authorID;

    String authorName;

    String phoneNumber;

    String email;

    Integer age;

    public Author(){}

    public Author(String authorName, String phoneNumber, String email, Integer age) {
        this.authorName = authorName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.age = age;
    }

    public Author(String authorID, String authorName, String phoneNumber, String email, Integer age) {
        this.authorID = authorID;
        this.authorName = authorName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
