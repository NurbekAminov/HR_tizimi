package HR_tizimi.config;

import HR_tizimi.entity.ProfileEntity;
import HR_tizimi.exception.AppBadRequestException;
import HR_tizimi.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ProfileRepository profileRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ProfileEntity> optional = profileRepository.findByUsername(username);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Login or password wrong");
        }
        ProfileEntity profileEntity = optional.get();
        return new CustomUserDetails(profileEntity);
    }
}
