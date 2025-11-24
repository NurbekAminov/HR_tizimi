package HR_tizimi.service;

import HR_tizimi.config.CustomUserDetails;
import HR_tizimi.dto.ApiResponse;
import HR_tizimi.dto.AttachDTO;
import HR_tizimi.dto.ProfileDTO;
import HR_tizimi.entity.ProfileEntity;
import HR_tizimi.enums.ProfileRole;
import HR_tizimi.enums.ProfileStatus;
import HR_tizimi.mapper.ProfileMapper;
import HR_tizimi.repository.ProfileRepository;
import HR_tizimi.util.MD5Util;
import HR_tizimi.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileMapper profileMapper;


    public ApiResponse create(ProfileDTO dto) {
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        Optional<ProfileEntity> profileByUsername = profileRepository.findByUsername(dto.getUsername());
        if (profileByUsername.isPresent()) {
            return new ApiResponse(false, "Username Alredy Exists");
        }

        ProfileEntity entity = profileMapper.toEntity(dto);
        entity.setPassword(MD5Util.encode(dto.getPassword()));
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setRole(ProfileRole.ROLE_ADMIN);
        entity.setPrtId(customUserDetails.getProfile().getId());
        profileRepository.save(entity);

        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());
        dto.setCreatedDate(entity.getCreatedDate());
        return new ApiResponse(true, dto);
    }


    public ApiResponse updateDetail(Integer profileId, ProfileDTO dto) {
        int effectRows = profileRepository.updateDetail(profileId, dto.getName(), dto.getSurname());
        if (effectRows == 0){
            return new ApiResponse(false, "Detail Not Updated");
        }
        return new ApiResponse(true, "Detail updated");
    }

    public ApiResponse updateUserName(String username) {
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        ProfileEntity profile = customUserDetails.getProfile();
        int effectRows = profileRepository.updateUsername(profile.getId(), username);
        if (effectRows == 0){
            return new ApiResponse(false, "username not updated");
        }
        return new ApiResponse(true, "username updated");
    }

    public ApiResponse changePassword(String newPassword) {
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        ProfileEntity profile = customUserDetails.getProfile();
        int effectRows = profileRepository.updatePassword(profile.getId(), newPassword);
        if (effectRows == 0){
            return new ApiResponse(false, "Password not updated");
        }
        return new ApiResponse(true, "Password updated");
    }

    public ApiResponse getDetail() {
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        ProfileEntity entity = customUserDetails.getProfile();
        ProfileDTO dto = profileMapper.toDTO(entity);
        return new ApiResponse(true, dto);
    }

    public ApiResponse updateAttach(Integer profileId, AttachDTO attachDTO) {
        int effectRows = profileRepository.updateAttach(profileId, attachDTO.getId(), attachDTO.getUrl());
        if (effectRows == 0){
            return new ApiResponse(false, "Image not updated");
        }
        return new ApiResponse(true, "Image updated");
    }
}
