package by.intexsoft.study.dto;

import java.util.List;

public class BookDTO {

    private Long id;
    private String bookName;
    private String publisher;
    private String publicationDate;
    private Boolean status;
    private List<AuthorDTO> authorDTOList;

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

    public List<AuthorDTO> getAuthorDTOList() {
        return authorDTOList;
    }

    public void setAuthorDTOList(List<AuthorDTO> authorDTOList) {
        this.authorDTOList = authorDTOList;
    }
}
