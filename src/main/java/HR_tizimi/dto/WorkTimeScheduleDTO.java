package HR_tizimi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkTimeScheduleDTO {
    private Integer id;
    private Integer profileId;
    private LocalTime inputTime;
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
