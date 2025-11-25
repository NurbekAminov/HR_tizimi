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
    private String name;
    private String surname;
    private String username;
    private String password;
    private ProfileRole role;
    private ProfileStatus status;

    public CustomUserDetails(ProfileEntity profile) {
        this.name = profile.getName();
        this.surname = profile.getName();
        this.username = profile.getUsername();
        this.password = profile.getPassword();
        this.role = profile.getRole();
        this.status = profile.getStatus();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(role.name()));

        return roles;
    }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return username; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return status.equals(ProfileStatus.ACTIVE); }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    public ProfileRole getRole() { return role; }

    public String getName() { return name; }

    public String getSurname() { return surname; }

    public ProfileStatus getStatus() {
        return status;
    }

    public void setStatus(ProfileStatus status) {
        this.status = status;
    }
}
