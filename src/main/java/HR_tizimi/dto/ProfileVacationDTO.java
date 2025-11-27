package HR_tizimi.dto;

import HR_tizimi.enums.VacationApplication;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileVacationDTO {
    private Integer id;

    @NotBlank(message = "ProfileId required")
    @NotNull(message = "ProfileId is null")
    private Integer profileId;
    private ProfileDTO profileDTO;
    @NotBlank(message = "vacationId required")
    @NotNull(message = "vacationId is null")
    private Integer vacationId;
    private VacationDTO vacationDTO;

    private Integer balance;

    private VacationApplication vacationApplication;

    private LocalDateTime createdDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public ProfileDTO getProfileDTO() {
        return profileDTO;
    }

    public void setProfileDTO(ProfileDTO profileDTO) {
        this.profileDTO = profileDTO;
    }

    public Integer getVacationId() {
        return vacationId;
    }

    public void setVacationId(Integer vacationId) {
        this.vacationId = vacationId;
    }

    public VacationDTO getVacationDTO() {
        return vacationDTO;
    }

    public void setVacationDTO(VacationDTO vacationDTO) {
        this.vacationDTO = vacationDTO;
    }

    public VacationApplication getVacationApplication() {
        return vacationApplication;
    }

    public void setVacationApplication(VacationApplication vacationApplication) {
        this.vacationApplication = vacationApplication;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
