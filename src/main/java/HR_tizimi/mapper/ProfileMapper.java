package HR_tizimi.mapper;

import HR_tizimi.dto.ProfileDTO;
import HR_tizimi.dto.RegistrationDTO;
import HR_tizimi.entity.ProfileEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileDTO toDTO(ProfileEntity entity);
    ProfileEntity toEntity(ProfileDTO dto);
    ProfileEntity toEntity(RegistrationDTO dto);
    List<ProfileDTO> toDTOList(List<ProfileEntity> entityList);
}
