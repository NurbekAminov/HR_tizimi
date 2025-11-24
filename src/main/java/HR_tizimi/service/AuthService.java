package HR_tizimi.service;

import HR_tizimi.dto.ApiResponse;
import HR_tizimi.dto.AuthDTO;
import HR_tizimi.dto.RegistrationDTO;
import HR_tizimi.entity.ProfileEntity;
import HR_tizimi.enums.ProfileStatus;
import HR_tizimi.mapper.ProfileMapper;
import HR_tizimi.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileMapper profileMapper;

    public ApiResponse login(AuthDTO dto) {
        Optional<ProfileEntity> byUsername = profileRepository.findByUsername(dto.getUsername());
        if (byUsername.isPresent()){
            ProfileEntity entity = byUsername.get();
            return new ApiResponse(true, profileMapper.toDTO(entity));
        }
        return new ApiResponse(false, "item not found");
    }

    public ApiResponse registration(RegistrationDTO dto) {
        Optional<ProfileEntity> optionalProfile = profileRepository.findByUsername(dto.getUsername());
        if (optionalProfile.isPresent()){
            if (optionalProfile.get().getStatus().equals(ProfileStatus.REGISTRATION)){
                profileRepository.delete(optionalProfile.get());
            } else {
                return new ApiResponse(false, "email already exists");
            }
        }

        // TODO
        /*Long count = emailHistoryRepository.countAllByToEmailAndCreatedDateAfter(dto.getEmail(), LocalDateTime.now().minusMinutes(1));
        if (count > 4) {
            return new ApiResponse(false, resourceBundleService.getMessage("try.again.around.1minute", lang));
        }*/

        ProfileEntity entity = profileMapper.toEntity(dto);
        profileRepository.save(entity);

        return new ApiResponse(true, "Registration completed");
    }
}
