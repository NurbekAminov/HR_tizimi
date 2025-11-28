package HR_tizimi.mapper;

import HR_tizimi.dto.VacationDTO;
import HR_tizimi.dto.WorkTimeDisruptionDTO;
import HR_tizimi.dto.WorkTimeHistoryDTO;
import HR_tizimi.dto.WorkTimeScheduleDTO;
import HR_tizimi.entity.VacationEntity;
import HR_tizimi.entity.WorkTimeDisruptionEntity;
import HR_tizimi.entity.WorkTimeHistoryEntity;
import HR_tizimi.entity.WorkTimeScheduleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkTimeMapper {
    WorkTimeScheduleDTO toWorkTimeScheduleDTO(WorkTimeScheduleEntity entity);
    WorkTimeDisruptionDTO toWorkTimeDisruptionDTO(WorkTimeDisruptionEntity entity);
    WorkTimeHistoryDTO toWorkTimeHistoryDTO(WorkTimeHistoryEntity entity);
}
