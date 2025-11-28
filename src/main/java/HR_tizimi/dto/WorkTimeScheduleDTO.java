package HR_tizimi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkTimeScheduleDTO {
    private Integer id;
    @NotBlank(message = "profileId required")
    @NotNull(message = "profileId is null")
    private Integer profileId;
    @NotBlank(message = "inputTime required")
    @NotNull(message = "inputTime is null")
    private LocalTime inputTime;
    @NotBlank(message = "outputTime required")
    @NotNull(message = "outputTime is null")
    private LocalTime outputTime;
    private Integer prtId;

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

    public LocalTime getInputTime() {
        return inputTime;
    }

    public void setInputTime(LocalTime inputTime) {
        this.inputTime = inputTime;
    }

    public LocalTime getOutputTime() {
        return outputTime;
    }

    public void setOutputTime(LocalTime outputTime) {
        this.outputTime = outputTime;
    }

    public Integer getPrtId() {
        return prtId;
    }

    public void setPrtId(Integer prtId) {
        this.prtId = prtId;
    }
}
