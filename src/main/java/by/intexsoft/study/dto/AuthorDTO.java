package by.intexsoft.study.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Author Entity")
public class AuthorDTO {
    @Schema(description = "ID")
    private Long id;
    @Schema(description = "Author name")
    private String authorName;
    @Schema(description = "Phone number")
    private String phoneNumber;
    @Schema(description = "Email")
    private String email;
    @Schema(description = "Age")
    private Integer age;
    @Schema(description = "Book(s)")
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
