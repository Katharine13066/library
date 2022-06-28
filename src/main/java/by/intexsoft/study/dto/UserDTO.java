package by.intexsoft.study.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User Entrity")
public class UserDTO{

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "User name")
    private String userName;

    @Schema(description = "Phone number")
    private String phoneNumber;

    @Schema(description = "Email")
    private String email;

    @Schema(description = "Birthday")
    private String birthday;

    @Schema(description = "Password Hash")
    private Long passwordHash;

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


