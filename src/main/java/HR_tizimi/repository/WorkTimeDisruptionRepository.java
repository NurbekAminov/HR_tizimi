package HR_tizimi.repository;

import HR_tizimi.entity.WorkTimeDisruptionEntity;
import HR_tizimi.entity.WorkTimeScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkTimeDisruptionRepository extends JpaRepository<WorkTimeDisruptionEntity, Integer> {
    @Query("from WorkTimeDisruptionEntity w where w.workTimeId =:workTimeId")
    Optional<WorkTimeDisruptionEntity> getByWorkTimeHistoryId(@Param("workTimeId") Integer workTimeId);
    @Query("from WorkTimeDisruptionEntity ")
    Optional<List<WorkTimeDisruptionEntity>> getDisruptionList();

}
