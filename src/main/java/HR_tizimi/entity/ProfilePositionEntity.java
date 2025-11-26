package HR_tizimi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Profile_Position")
public class ProfilePositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "profileId", nullable = false)
    private Integer profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profileId", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name = "positionId", nullable = false)
    private Integer positionId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "positionId", insertable = false, updatable = false)
    private PositionEntity position;

    @Column(name = "prtId")
    private Integer prtId;

    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;

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

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public PositionEntity getPosition() {
        return position;
    }

    public void setPosition(PositionEntity position) {
        this.position = position;
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
