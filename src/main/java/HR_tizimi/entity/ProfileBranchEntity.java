package HR_tizimi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Profile_Branch")
public class ProfileBranchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "profileId", nullable = false)
    private Integer profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profileId", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name = "branchId", nullable = false)
    private Integer branchId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branchId", insertable = false, updatable = false)
    private BranchEntity branch;

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

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public BranchEntity getBranch() {
        return branch;
    }

    public void setBranch(BranchEntity branch) {
        this.branch = branch;
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
