package HR_tizimi.mapper;

import HR_tizimi.dto.PositionDTO;
import HR_tizimi.dto.ProfilePositionDTO;
import HR_tizimi.entity.PositionEntity;
import HR_tizimi.entity.ProfilePositionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    ProfilePositionDTO toPositionDTO(ProfilePositionEntity entity);
    PositionDTO toPositionDTO(PositionEntity entity);
}
