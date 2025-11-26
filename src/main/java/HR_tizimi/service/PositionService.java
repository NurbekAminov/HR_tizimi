package HR_tizimi.service;

import HR_tizimi.config.CustomUserDetails;
import HR_tizimi.dto.*;
import HR_tizimi.entity.*;
import HR_tizimi.repository.*;
import HR_tizimi.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PositionService {
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfilePositionRepository profilePositionRepository;

    public PositionDTO create(PositionDTO dto){
        Optional<PositionEntity> optional = positionRepository.findByPositionName(dto.getName());
        if (optional.isPresent()){
            return null;
        }
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();

        PositionEntity entity = new PositionEntity();
        entity.setName(dto.getName());
        entity.setPrtId(customUserDetails.getProfile().getId());
        entity.setCreatedDate(LocalDateTime.now());
        positionRepository.save(entity);

        dto.setId(entity.getId());
        dto.setPrtId(entity.getPrtId());
        dto.setCreatedDate(dto.getCreatedDate());

        return dto;
    }

    public ApiResponse update(PositionDTO dto){
        Optional<PositionEntity> byId = positionRepository.findByPositionId(dto.getId());
        if (byId.isEmpty()){
            return null;
        }

        Optional<PositionEntity> byName = positionRepository.findByPositionName(dto.getName());
        if (byName.isPresent()){
            return null;
        }

        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        int effectRows = positionRepository.update(dto.getId(), dto.getName(), customUserDetails.getProfile().getId());
        if (effectRows == 0){
            return new ApiResponse(false, "Position Not Updated");
        }
        return new ApiResponse(true, "Position Updated");
    }

    public ApiResponse delete(PositionDTO dto){
        Optional<PositionEntity> byName = positionRepository.findByPositionId(dto.getId());
        if (byName.isEmpty()){
            return null;
        }

        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        int effectRows = positionRepository.delete(dto.getId(), customUserDetails.getProfile().getId());
        if (effectRows == 0){
            return new ApiResponse(false, "Position Not Deleted");
        }
        return new ApiResponse(true, "Position Deleted");
    }

    public ApiResponse join(ProfilePositionDTO dto){
        Optional<PositionEntity> branch = positionRepository.findByPositionId(dto.getPositionId());
        if (branch.isEmpty()){
            return new ApiResponse(false, "Position not exist");
        }

        Optional<ProfileEntity> profile = profileRepository.findByProfileId(dto.getProfileId());
        if (profile.isEmpty()){
            return new ApiResponse(false, "Profile not exist");
        }


        Optional<ProfilePositionEntity> branchProfile = profilePositionRepository.findByPositionIdAndProfileId(dto.getPositionId(), dto.getProfileId());
        if (branchProfile.isPresent()){
            return new ApiResponse(false, "Profile already exist in Position");
        }

        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();

        ProfilePositionEntity profilePositionEntity = new ProfilePositionEntity();
        profilePositionEntity.setPositionId(dto.getPositionId());
        profilePositionEntity.setProfileId(dto.getProfileId());
        profilePositionEntity.setPrtId(customUserDetails.getProfile().getId());
        profilePositionEntity.setCreatedDate(LocalDateTime.now());

        profilePositionRepository.save(profilePositionEntity);
        return new ApiResponse(true, "Profile subscribed to Position");
    }


}
