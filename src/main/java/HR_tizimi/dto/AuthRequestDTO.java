package HR_tizimi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthRequestDTO {
    @NotNull(message = "username is null!")
    @NotBlank(message = "username is blank!")
    private String username;
    @NotNull(message = "password is null!")
    @NotBlank(message = "password is blank!")
    @Size(min = 8, message = "password should be minimum 8!")
    private String password;

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
}
