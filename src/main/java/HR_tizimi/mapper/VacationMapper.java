package HR_tizimi.mapper;

import HR_tizimi.dto.PositionDTO;
import HR_tizimi.dto.ProfilePositionDTO;
import HR_tizimi.dto.ProfileVacationDTO;
import HR_tizimi.dto.VacationDTO;
import HR_tizimi.entity.PositionEntity;
import HR_tizimi.entity.ProfilePositionEntity;
import HR_tizimi.entity.ProfileVacationEntity;
import HR_tizimi.entity.VacationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VacationMapper {
    ProfileVacationDTO toVacationDTO(ProfileVacationEntity entity);
    VacationDTO toVacationDTO(VacationEntity entity);
}
