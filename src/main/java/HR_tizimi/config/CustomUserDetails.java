package HR_tizimi.config;

import HR_tizimi.entity.ProfileEntity;
import HR_tizimi.enums.ProfileRole;
import HR_tizimi.enums.ProfileStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final ProfileEntity profile;

    public CustomUserDetails(ProfileEntity profile) {
        this.profile = profile;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(profile.getRole().name()));

        return roles;
    }

    @Override
    public String getPassword() { return profile.getPassword(); }

    @Override
    public String getUsername() { return profile.getUsername(); }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return profile.getStatus().equals(ProfileStatus.ACTIVE); }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    public ProfileRole getRole() { return profile.getRole(); }

    public String getName() { return profile.getName(); }

    public String getSurname() { return profile.getSurname(); }

    public ProfileEntity getProfile() {
        return profile;
    }
}
