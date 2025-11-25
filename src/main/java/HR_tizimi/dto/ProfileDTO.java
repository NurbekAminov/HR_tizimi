package HR_tizimi.dto;

import HR_tizimi.enums.ProfilePosition;
import HR_tizimi.enums.ProfileRole;
import HR_tizimi.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    private Integer id;

    @NotBlank(message = "Name required")
    @NotNull(message = "name is null")
    @Size(min = 3, message = "Name should be minimum 3")
    private String name;
    private String surname;

    @NotNull(message = "username is null!")
    @NotBlank(message = "username required")
    private String username;
    @NotBlank(message = "Password required")
    @NotNull(message = "Password is null!")
    @Size(min = 8, message = "password should be minimum 8!")
    private String password;

    private AttachDTO photo;
    private String photoUrl;

    @NotBlank(message = "Role required")
    private ProfileRole role;
    @NotBlank(message = "Status required")
    private ProfileStatus status;
    @NotBlank(message = "Position required")
    private ProfilePosition position;
    private LocalDateTime createdDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public AttachDTO getPhoto() {
        return photo;
    }

    public void setPhoto(AttachDTO photo) {
        this.photo = photo;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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

    public ProfilePosition getPosition() {
        return position;
    }

    public void setPosition(ProfilePosition position) {
        this.position = position;
    }
}
