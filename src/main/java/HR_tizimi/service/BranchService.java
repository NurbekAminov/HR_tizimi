package HR_tizimi.service;

import HR_tizimi.config.CustomUserDetails;
import HR_tizimi.dto.ApiResponse;
import HR_tizimi.dto.BranchDTO;
import HR_tizimi.dto.ProfileBranchDTO;
import HR_tizimi.entity.BranchEntity;
import HR_tizimi.entity.ProfileBranchEntity;
import HR_tizimi.entity.ProfileEntity;
import HR_tizimi.mapper.BranchMapper;
import HR_tizimi.repository.ProfileBranchRepository;
import HR_tizimi.repository.BranchRepository;
import HR_tizimi.repository.ProfileRepository;
import HR_tizimi.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileBranchRepository profileBranchRepository;
    @Autowired
    private BranchMapper branchMapper;

    public BranchDTO create(BranchDTO dto){
        Optional<BranchEntity> optional = branchRepository.findByBranchName(dto.getName());
        if (optional.isPresent()){
            return null;
        }
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();

        BranchEntity entity = new BranchEntity();
        entity.setName(dto.getName());
        entity.setPrtId(customUserDetails.getProfile().getId());
        entity.setCreatedDate(LocalDateTime.now());
        branchRepository.save(entity);

        dto.setId(entity.getId());
        dto.setPrtId(entity.getPrtId());
        dto.setCreatedDate(dto.getCreatedDate());

        return dto;
    }

    public List<BranchDTO> get() {
        Optional<List<BranchEntity>> optional = branchRepository.getBranchList();
        if (optional.isEmpty()){
            return null;
        }

        List<BranchEntity> entityList = optional.get();

        List<BranchDTO> dtoList = new LinkedList<>();
        for (BranchEntity entity: entityList){
            BranchDTO dto = branchMapper.toBranchDTO(entity);
            dtoList.add(dto);
        }

        return dtoList;
    }

    public ApiResponse updateName(BranchDTO dto){
        Optional<BranchEntity> byId = branchRepository.findByBranchId(dto.getId());
        if (byId.isEmpty()){
            return null;
        }

        Optional<BranchEntity> newName = branchRepository.findByBranchName(dto.getName());
        if (newName.isPresent()){
            return null;
        }

        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        int effectRows = branchRepository.update(dto.getId(), dto.getName(), customUserDetails.getProfile().getId());
        if (effectRows == 0){
            return new ApiResponse(false, "Branch Not Updated");
        }
        return new ApiResponse(true, "Branch Updated");
    }

    public ApiResponse delete(BranchDTO dto){
        Optional<BranchEntity> byName = branchRepository.findByBranchId(dto.getId());
        if (byName.isEmpty()){
            return null;
        }

        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        int effectRows = branchRepository.delete(dto.getId(), customUserDetails.getProfile().getId());
        if (effectRows == 0){
            return new ApiResponse(false, "Branch Not Deleted");
        }
        return new ApiResponse(true, "Branch Deleted");
    }

    public ApiResponse join(ProfileBranchDTO dto){
        Optional<BranchEntity> branch = branchRepository.findByBranchId(dto.getBranchId());
        if (branch.isEmpty()){
            return new ApiResponse(false, "Branch not exist");
        }

        Optional<ProfileEntity> profile = profileRepository.findByProfileId(dto.getProfileId());
        if (profile.isEmpty()){
            return new ApiResponse(false, "Profile not exist");
        }

        Optional<ProfileBranchEntity> branchProfile = profileBranchRepository.findByBranchIdAndProfileId(dto.getBranchId(), dto.getProfileId());
        if (branchProfile.isPresent()){
            return new ApiResponse(false, "Profile already exist in branch");
        }

        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();

        ProfileBranchEntity profileBranchEntity = new ProfileBranchEntity();
        profileBranchEntity.setBranchId(dto.getBranchId());
        profileBranchEntity.setProfileId(dto.getProfileId());
        profileBranchEntity.setPrtId(customUserDetails.getProfile().getId());
        profileBranchEntity.setCreatedDate(LocalDateTime.now());

        profileBranchRepository.save(profileBranchEntity);
        return new ApiResponse(true, "Profile subscribed to branch");
    }

    public ProfileBranchDTO FilterProfileByBranchAndPosition(){
        return null;
    }
}
