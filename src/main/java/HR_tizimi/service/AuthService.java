package HR_tizimi.service;

import HR_tizimi.config.CustomUserDetails;
import HR_tizimi.dto.AuthRequestDTO;
import HR_tizimi.dto.AuthResponseDTO;
import HR_tizimi.dto.ProfileDTO;
import HR_tizimi.entity.ProfileEntity;
import HR_tizimi.enums.ProfileRole;
import HR_tizimi.enums.ProfileStatus;
import HR_tizimi.mapper.ProfileMapper;
import HR_tizimi.repository.ProfileRepository;
import HR_tizimi.util.JWTUtil;
import HR_tizimi.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileMapper profileMapper;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponseDTO login(AuthRequestDTO dto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

            if (authentication.isAuthenticated()) {
                CustomUserDetails profile = (CustomUserDetails) authentication.getPrincipal();
                AuthResponseDTO response = new AuthResponseDTO();
                response.setName(profile.getName());
                response.setSurname(profile.getSurname());
                response.setUsername(profile.getUsername());
                response.setRole(profile.getRole());
                response.setJwtToken(JWTUtil.encode(profile.getUsername(), profile.getRole().name()));
                return response;
            }
        } catch (BadCredentialsException e) {
            throw new UsernameNotFoundException("Phone or password wrong");
        }
        throw new UsernameNotFoundException("Phone or password wrong");
    }

    public ProfileDTO registration(ProfileDTO dto) {
        Optional<ProfileEntity> optionalProfile = profileRepository.findByUsernameAndVisibleTrue(dto.getUsername());
        if (optionalProfile.isPresent()) {
            return null;
        }

        ProfileEntity entity = profileMapper.toEntity(dto);
        entity.setPassword(MD5Util.encode(dto.getPassword()));
        entity.setRole(ProfileRole.ROLE_USER);
        entity.setStatus(ProfileStatus.ACTIVE);
        profileRepository.save(entity);

//        return new ApiResponse(true, "Registration completed");
        return profileMapper.toDTO(entity);
    }
}
