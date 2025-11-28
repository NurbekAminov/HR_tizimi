package HR_tizimi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkTimeDisruptionDTO {
    private Integer id;
    private Integer profileId;
    private Integer workTimeId;
    private LocalTime missTime;
    private LocalTime earlyLeftTime;

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

    public Integer getWorkTimeId() {
        return workTimeId;
    }

    public void setWorkTimeId(Integer workTimeId) {
        this.workTimeId = workTimeId;
    }

    public LocalTime getMissTime() {
        return missTime;
    }

    public void setMissTime(LocalTime missTime) {
        this.missTime = missTime;
    }

    public LocalTime getEarlyLeftTime() {
        return earlyLeftTime;
    }

    public void setEarlyLeftTime(LocalTime earlyLeftTime) {
        this.earlyLeftTime = earlyLeftTime;
    }
}
