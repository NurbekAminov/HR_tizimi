package HR_tizimi.entity;

import HR_tizimi.enums.VacationApplication;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Profile_Vacation")
public class ProfileVacationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "profileId", nullable = false)
    private Integer profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profileId", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name = "vacationId", nullable = false)
    private Integer vacationId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacationId", insertable = false, updatable = false)
    private VacationEntity vacation;

    @Column(name = "balance")
    private Integer balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "vacationApplication")
    private VacationApplication vacationApplication;

    @Column(name = "prtId")
    private Integer prtId;

    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;

    @Column(name = "verifyDate")
    private LocalDateTime verifyDate;

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

    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }

    public Integer getVacationId() {
        return vacationId;
    }

    public void setVacationId(Integer vacationId) {
        this.vacationId = vacationId;
    }

    public VacationEntity getVacation() {
        return vacation;
    }

    public void setVacation(VacationEntity vacation) {
        this.vacation = vacation;
    }

    public VacationApplication getVacationApplication() {
        return vacationApplication;
    }

    public void setVacationApplication(VacationApplication vacationApplication) {
        this.vacationApplication = vacationApplication;
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

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public LocalDateTime getVerifyDate() {
        return verifyDate;
    }

    public void setVerifyDate(LocalDateTime verifyDate) {
        this.verifyDate = verifyDate;
    }
}
