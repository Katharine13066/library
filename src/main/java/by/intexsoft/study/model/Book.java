package by.intexsoft.study.model;

public class Book {
    private String bookID;
    private String bookName;
    private String authorID;
    private String publisher;
    private String publicationDate;

    public Book(){}

    public Book(String bookID, String bookName, String authorID, String publisher, String publicationDate) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.authorID = authorID;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
    }

    public Book(String bookName, String authorID, String publisher, String publicationDate) {
        this.bookName = bookName;
        this.authorID = authorID;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
    }
    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }


    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
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

    @Override
    public String toString() {
        return "Book{" +
                "bookID='" + bookID + '\'' +
                ", bookName='" + bookName + '\'' +
                ", authorID='" + authorID + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                '}';
    }
}
