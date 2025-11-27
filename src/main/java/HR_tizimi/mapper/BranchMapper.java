package HR_tizimi.mapper;

import HR_tizimi.dto.BranchDTO;
import HR_tizimi.dto.ProfileBranchDTO;
import HR_tizimi.entity.BranchEntity;
import HR_tizimi.entity.ProfileBranchEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchMapper {
    ProfileBranchDTO toBranchDTO(ProfileBranchEntity entity);
    BranchDTO toBranchDTO(BranchEntity entity);
}
