package HR_tizimi.mapper;

import HR_tizimi.dto.ProfilePositionDTO;
import HR_tizimi.entity.ProfilePositionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfilePositionMapper {
    ProfilePositionDTO toDTO(ProfilePositionEntity entity);
}
