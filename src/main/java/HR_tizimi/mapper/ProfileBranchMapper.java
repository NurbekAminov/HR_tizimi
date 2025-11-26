package HR_tizimi.mapper;

import HR_tizimi.dto.ProfileBranchDTO;
import HR_tizimi.entity.ProfileBranchEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileBranchMapper {
    ProfileBranchDTO toDTO(ProfileBranchEntity entity);
}
