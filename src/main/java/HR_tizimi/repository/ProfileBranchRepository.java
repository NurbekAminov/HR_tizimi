package HR_tizimi.repository;

import HR_tizimi.entity.ProfileBranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileBranchRepository extends JpaRepository<ProfileBranchEntity, Integer> {

    @Query("from ProfileBranchEntity b where b.branchId =:branchId and b.profileId =:profileId and b.visible = true ")
    Optional<ProfileBranchEntity> findByBranchIdAndProfileId(@Param("branchId") Integer branchId, @Param("profileId") Integer profileId);
}
