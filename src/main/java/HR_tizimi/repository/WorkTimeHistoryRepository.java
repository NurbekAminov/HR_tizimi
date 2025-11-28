package HR_tizimi.repository;

import HR_tizimi.entity.WorkTimeHistoryEntity;
import HR_tizimi.enums.WorkTimeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface WorkTimeHistoryRepository extends JpaRepository<WorkTimeHistoryEntity, Integer> {
    @Query("from WorkTimeHistoryEntity w where w.profileId =:profileId and w.workTimeStatus =:workTimeStatus")
    Optional<WorkTimeHistoryEntity> findByProfileIdAndWorkTimeStatus(@Param("profileId") Integer profileId, @Param("workTimeStatus") WorkTimeStatus workTimeStatus);
    @Query("from WorkTimeHistoryEntity ")
    Optional<List<WorkTimeHistoryEntity>> getHistoryList();

    @Query("SELECT w FROM WorkTimeHistoryEntity w WHERE " +
            "w.createdDate >= :startDate AND w.createdDate <= :endDate ORDER BY w.createdDate ")
    Optional<List<WorkTimeHistoryEntity>> getWeekHistoryList(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT w FROM WorkTimeHistoryEntity w WHERE " +
            "FUNCTION('YEAR', w.createdDate) = :year AND FUNCTION('MONTH', w.createdDate) = :month ORDER BY w.createdDate")
    Optional<List<WorkTimeHistoryEntity>> getMonthHistoryList(@Param("year") Integer year, @Param("month") Integer month);
}
