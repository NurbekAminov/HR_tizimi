package HR_tizimi.service;

import HR_tizimi.config.CustomUserDetails;
import HR_tizimi.dto.*;
import HR_tizimi.entity.ProfileEntity;
import HR_tizimi.enums.ProfilePosition;
import HR_tizimi.enums.ProfileRole;
import HR_tizimi.enums.ProfileStatus;
import HR_tizimi.mapper.ProfileMapper;
import HR_tizimi.repository.ProfileCustomRepository;
import HR_tizimi.repository.ProfileRepository;
import HR_tizimi.util.MD5Util;
import HR_tizimi.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileCustomRepository profileCustomRepository;
    @Autowired
    private ProfileMapper profileMapper;


    public ApiResponse create(ProfileDTO dto) {
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        Optional<ProfileEntity> profileByUsername = profileRepository.findByUsernameAndVisibleTrue(dto.getUsername());
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


    public ApiResponse updateDetail(ProfileDTO dto) {
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        int effectRows = profileRepository.updateDetail(customUserDetails.getProfile().getId(), dto.getName(), dto.getSurname());
        if (effectRows == 0){
            return new ApiResponse(false, "Detail Not Updated");
        }
        return new ApiResponse(true, "Detail updated");
    }

    public ApiResponse updateUsername(String username) {
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        int effectRows = profileRepository.updateUsername(customUserDetails.getProfile().getId(), username);
        if (effectRows == 0){
            return new ApiResponse(false, "username not updated");
        }
        return new ApiResponse(true, "username updated");
    }

    public ApiResponse changePassword(String newPassword) {
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        ProfileEntity profile = customUserDetails.getProfile();
        int effectRows = profileRepository.updatePassword(profile.getId(), MD5Util.encode(newPassword));
        if (effectRows == 0){
            return new ApiResponse(false, "Password not updated");
        }
        return new ApiResponse(true, "Password updated");
    }

    public ProfileDTO getDetail() {
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        Optional<ProfileEntity> optionalProfile = profileRepository.getDetail(customUserDetails.getProfile().getId());
        if (optionalProfile.isEmpty()){
            return null;
        }
        ProfileEntity entity = optionalProfile.get();
        ProfileDTO dto = profileMapper.toDTO(entity);
        return dto;
    }

    public ApiResponse delete() {
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        int effectRows = profileRepository.delete(customUserDetails.getProfile().getId());
        if (effectRows == 0){
            return new ApiResponse(false, "Profile not deleted");
        }
        return new ApiResponse(true, "Profile deleted");
    }

    public PageImpl<ProfileDTO> pagination(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdDate").ascending());
        Page<ProfileEntity> result = profileRepository.findAll(pageRequest);

        List<ProfileDTO> dtoList = new LinkedList<>();
        for (ProfileEntity entity: result){
            ProfileDTO dto = profileMapper.toDTO(entity);
            dtoList.add(dto);
        }

        long total = result.getTotalElements();
        return new PageImpl<>(dtoList, pageRequest, total);
    }

    public PageImpl<ProfileDTO> filterProfile(ProfileFilterDTO filter, int page, int size){
        Page<ProfileEntity> result = profileCustomRepository.filter(filter, page, size);

        long totalCount = result.getTotalElements();
        List<ProfileEntity> entityList = result.getContent();

        List<ProfileDTO> dtoList = new LinkedList<>();
        for (ProfileEntity entity: entityList){
            ProfileDTO dto = profileMapper.toDTO(entity);
            dtoList.add(dto);
        }

        PageRequest pageRequest = PageRequest.of(page, size);
        return new PageImpl<>(dtoList, pageRequest, totalCount);
    }

    public ApiResponse updateAttach(Integer profileId, AttachDTO attachDTO) {
        int effectRows = profileRepository.updateAttach(profileId, attachDTO.getId(), attachDTO.getUrl());
        if (effectRows == 0){
            return new ApiResponse(false, "Image not updated");
        }
        return new ApiResponse(true, "Image updated");
    }
}
