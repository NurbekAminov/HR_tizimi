package HR_tizimi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
@Getter
@Setter
@Entity
@Table(name = "WorkTimeSchedule")
public class WorkTimeScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "profileId")
    private Integer profileId;

    @Column(name = "inputTime")
    private LocalTime inputTime;

    @Column(name = "outputTime")
    private LocalTime outputTime;

    @Column(name = "prtId")
    private Integer prtId;

    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

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

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
