package by.intexsoft.study.dto;

import java.util.List;

public class AuthorDTO {

    private Long id;
    private String authorName;
    private String phoneNumber;
    private String email;
    private Integer age;

    private List<BookDTO> bookDTOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<BookDTO> getBookDTOList() {
        return bookDTOList;
    }

    public void setBookDTOList(List<BookDTO> bookDTOList) {
        this.bookDTOList = bookDTOList;
    }
}
