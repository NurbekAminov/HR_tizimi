package HR_tizimi.repository;

import HR_tizimi.entity.ProfileVacationEntity;
import HR_tizimi.enums.VacationApplication;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileVacationRepository extends JpaRepository<ProfileVacationEntity, Integer> {
    @Query("from ProfileVacationEntity p where p.vacationId =:vacationId and p.profileId =:profileId and p.visible = true ")
    Optional<ProfileVacationEntity> findByVacationIdAndProfileId(@Param("vacationId") Integer vacationId, @Param("profileId") Integer profileId);

    @Query("from ProfileVacationEntity p where p.profileId =:profileId and p.visible = true ")
    Optional<ProfileVacationEntity> findByProfileId(@Param("profileId") Integer profileId);

    @Transactional
    @Modifying
    @Query("update ProfileVacationEntity p set p.prtId =:prtId, p.vacationApplication =:vacationApplication where p.id =:id")
    int verify(@Param("id") Integer id, @Param("prtId") Integer prtId, @Param("vacationApplication") String vacationApplication);
}
