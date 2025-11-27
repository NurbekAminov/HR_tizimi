package HR_tizimi.repository;

import HR_tizimi.entity.ProfilePositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfilePositionRepository extends JpaRepository<ProfilePositionEntity, Integer> {
    @Query("from ProfilePositionEntity p where p.positionId =:positionId and p.profileId =:profileId and p.visible = true ")
    Optional<ProfilePositionEntity> findByPositionIdAndProfileId(@Param("positionId") Integer positionId, @Param("profileId") Integer profileId);
}
