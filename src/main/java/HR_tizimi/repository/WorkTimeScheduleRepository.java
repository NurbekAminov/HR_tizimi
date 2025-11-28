package HR_tizimi.repository;

import HR_tizimi.entity.PositionEntity;
import HR_tizimi.entity.WorkTimeScheduleEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface WorkTimeScheduleRepository extends JpaRepository<WorkTimeScheduleEntity, Integer> {
    @Query("from WorkTimeScheduleEntity w where w.id =:id and w.visible = true ")
    Optional<WorkTimeScheduleEntity> findByScheduleId(@Param("id") Integer id);

    @Query("from WorkTimeScheduleEntity w where w.profileId =:profileId and w.visible = true ")
    Optional<WorkTimeScheduleEntity> findByProfileId(@Param("profileId") Integer profileId);

    @Query("from WorkTimeScheduleEntity w where w.visible = true ")
    Optional<List<WorkTimeScheduleEntity>> getScheduleList();

    @Transactional
    @Modifying
    @Query("update WorkTimeScheduleEntity w set w.prtId =:prtId, w.inputTime =:inputTime, w.outputTime =:outputTime where w.profileId =:profileId")
    int updateSchedule(@Param("profileId") Integer profileId, @Param("prtId") Integer prtId,@Param("inputTime") LocalTime inputTime,@Param("outputTime") LocalTime outputTime);

    @Transactional
    @Modifying
    @Query("update WorkTimeScheduleEntity w set w.visible = false, w.prtId =:prtId where w.id =:id")
    int deleteSchedule(@Param("id") Integer id, @Param("prtId") Integer prtId);
}
