package HR_tizimi.entity;

import HR_tizimi.enums.WorkTimeStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
@Getter
@Setter
@Entity
@Table(name = "WorkTimeHistory")
public class WorkTimeHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "profileId")
    private Integer profileId;

    @Column(name = "inputTime")
    private LocalTime inputTime;

    @Column(name = "outputTime")
    private LocalTime outputTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "workTimeStatus")
    private WorkTimeStatus workTimeStatus;

    @Column(name = "createdDate")
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
