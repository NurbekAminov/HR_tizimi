package HR_tizimi.dto;

import HR_tizimi.enums.ProfileRole;
import HR_tizimi.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationDTO {
    @NotBlank(message = "Name required")
    @NotNull(message = "name is null")
    private String name;
    private String surname;

    @NotBlank(message = "username required")
    @NotNull(message = "username is null")
    private String username;
    @NotBlank(message = "password required")
    @NotNull(message = "password is null")
    @Size(min = 8, message = "password should be minimum 8 character!")
    private String password;

    @NotBlank(message = "photo is required")
    private String photoId;

    @NotBlank(message = "role is required")
    @NotNull(message = "role is null")
    private ProfileRole role;
    private ProfileStatus status;

    private LocalDateTime createdDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public ProfileRole getRole() {
        return role;
    }

    public void setRole(ProfileRole role) {
        this.role = role;
    }

    public ProfileStatus getStatus() {
        return status;
    }

    public void setStatus(ProfileStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
