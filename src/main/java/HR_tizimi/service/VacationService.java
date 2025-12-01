package HR_tizimi.service;

import HR_tizimi.config.CustomUserDetails;
import HR_tizimi.dto.ApiResponse;
import HR_tizimi.dto.ProfileDTO;
import HR_tizimi.dto.ProfileVacationDTO;
import HR_tizimi.dto.VacationDTO;
import HR_tizimi.entity.ProfileEntity;
import HR_tizimi.entity.ProfileVacationEntity;
import HR_tizimi.entity.VacationEntity;
import HR_tizimi.enums.VacationApplication;
import HR_tizimi.mapper.VacationMapper;
import HR_tizimi.repository.ProfileRepository;
import HR_tizimi.repository.ProfileVacationRepository;
import HR_tizimi.repository.VacationRepository;
import HR_tizimi.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class VacationService {
    @Autowired
    private VacationRepository vacationRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileVacationRepository profileVacationRepository;
    @Autowired
    private VacationMapper vacationMapper;

    public VacationDTO create(VacationDTO dto){
        Optional<VacationEntity> optional = vacationRepository.findByVacationName(dto.getName());
        if (optional.isPresent()){
            return null;
        }
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();

        VacationEntity entity = new VacationEntity();
        entity.setName(dto.getName());
        entity.setBalance(dto.getBalance());
        entity.setPrtId(customUserDetails.getProfile().getId());
        entity.setCreatedDate(LocalDateTime.now());
        vacationRepository.save(entity);

        dto.setId(entity.getId());
        dto.setPrtId(entity.getPrtId());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }

    public List<VacationDTO> getAll() {
        Optional<List<VacationEntity>> optional = vacationRepository.getVacationList();
        if (optional.isEmpty()){
            return null;
        }

        List<VacationEntity> entityList = optional.get();

        List<VacationDTO> dtoList = new LinkedList<>();
        for (VacationEntity entity: entityList){
            VacationDTO dto = vacationMapper.toVacationDTO(entity);
            dtoList.add(dto);
        }

        return dtoList;
    }

    public VacationDTO getById(Integer id) {
        VacationEntity entity = vacationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topilmadi"));
        VacationDTO dto = new VacationDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBalance(entity.getBalance());
        return dto;
    }

    public ApiResponse update(VacationDTO dto){
        Optional<VacationEntity> byId = vacationRepository.findByVacationId(dto.getId());
        if (byId.isEmpty()){
            return new ApiResponse(false, "Vacation not found");
        }

        Optional<VacationEntity> newName = vacationRepository.findByVacationName(dto.getId(), dto.getName());
        if (newName.isPresent()){
            return new ApiResponse(false, "This name already exist");
        }

        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        int effectRows = vacationRepository.update(dto.getId(), dto.getName(), dto.getBalance(), customUserDetails.getProfile().getId());
        if (effectRows == 0){
            return new ApiResponse(false, "Vacation Not Updated");
        }
        return new ApiResponse(true, "Vacation Updated");
    }

    public ApiResponse updateStatus(ProfileVacationDTO dto){
        Optional<ProfileVacationEntity> optional = profileVacationRepository.findByVacationIdAndProfileId(dto.getVacationId(), dto.getProfileId());
        if (optional.isEmpty()){
            return new ApiResponse(false, "Vacation or Profile not found");
        }

        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        ProfileVacationEntity entity = optional.get();

        if (entity.getVacationApplication() == VacationApplication.ACTIVE){
            entity.setVacationApplication(VacationApplication.NOT_ACTIVE);

            LocalDateTime verifyDate = entity.getVerifyDate();
            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(verifyDate, now);
            Integer balance = entity.getBalance() - (int) duration.toDays();

            entity.setBalance(balance);
        }else {
            entity.setVacationApplication(VacationApplication.ACTIVE);
        }

        entity.setPrtId(customUserDetails.getProfile().getId());
        entity.setVerifyDate(LocalDateTime.now());
        profileVacationRepository.save(entity);

        return new ApiResponse(true, "Vacation Updated");
    }

    public ApiResponse delete(VacationDTO dto){
        Optional<VacationEntity> byName = vacationRepository.findByVacationId(dto.getId());
        if (byName.isEmpty()){
            return new ApiResponse(false, "Vacation not found");
        }

        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        int effectRows = vacationRepository.delete(dto.getId(), customUserDetails.getProfile().getId());
        if (effectRows == 0){
            return new ApiResponse(false, "Vacation Not Deleted");
        }
        return new ApiResponse(true, "Vacation Deleted");
    }

    public ApiResponse join(ProfileVacationDTO dto){
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();

        Optional<VacationEntity> vacation = vacationRepository.findByVacationId(dto.getVacationId());
        if (vacation.isEmpty()){
            return new ApiResponse(false, "Vacation not exist");
        }

        Optional<ProfileEntity> profile = profileRepository.findByProfileId(customUserDetails.getProfile().getId());
        if (profile.isEmpty()){
            return new ApiResponse(false, "Profile not exist");
        }

        Optional<ProfileVacationEntity> vacationProfile = profileVacationRepository.findByVacationIdAndProfileId(dto.getVacationId(), customUserDetails.getProfile().getId());
        if (vacationProfile.isPresent()){
            return new ApiResponse(false, "Profile already exist in this Vacation");
        }

        vacationProfile = profileVacationRepository.findByProfileId(customUserDetails.getProfile().getId());
        if (vacationProfile.isPresent()){
            return new ApiResponse(false, "Profile exist in other Vacation");
        }

        ProfileVacationEntity profileVacationEntity = new ProfileVacationEntity();
        profileVacationEntity.setVacationId(dto.getVacationId());
        profileVacationEntity.setProfileId(customUserDetails.getProfile().getId());
        profileVacationEntity.setBalance(vacation.get().getBalance());
        profileVacationEntity.setVacationApplication(VacationApplication.NOT_ACTIVE);
        profileVacationEntity.setCreatedDate(LocalDateTime.now());

        profileVacationRepository.save(profileVacationEntity);
        return new ApiResponse(true, "Profile send application for Vacation");
    }

    public Integer getProfileVacationBalance(){
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();

        Optional<ProfileVacationEntity> optional = profileVacationRepository.findByProfileId(customUserDetails.getProfile().getId());

        if (optional.isEmpty()){
            return null;
        }

        ProfileVacationEntity entity = optional.get();

        if (entity.getVacationApplication() == VacationApplication.NOT_ACTIVE){
            return null;
        }

        LocalDateTime verifyDate = entity.getVerifyDate();
        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(verifyDate, now);

        Integer balance = entity.getBalance() - (int) duration.toDays();

        return balance;
    }
}
