package HR_tizimi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
@Getter
@Setter
@Entity
@Table(name = "WorkTimeDisruption")
public class WorkTimeDisruptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "profileId")
    private Integer profileId;

    @Column(name = "workTimeId")
    private Integer workTimeId;

    @Column(name = "missTime")
    private LocalTime missTime;

    @Column(name = "earlyLeftTime")
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
