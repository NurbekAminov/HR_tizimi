package HR_tizimi.dto;

import HR_tizimi.enums.WorkTimeStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkTimeHistoryDTO {
    private Integer id;
    private Integer profileId;
    private LocalTime inputTime;
    private LocalTime outputTime;
    private WorkTimeStatus workTimeStatus;
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

    public WorkTimeStatus getWorkTimeStatus() {
        return workTimeStatus;
    }

    public void setWorkTimeStatus(WorkTimeStatus workTimeStatus) {
        this.workTimeStatus = workTimeStatus;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
