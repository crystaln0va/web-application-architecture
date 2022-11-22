package com.example.property_management.Security;

import com.example.property_management.entity.Property;
import com.example.property_management.entity.Role;
import com.example.property_management.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Component
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {


    private long id;
    private String email;
    @JsonIgnore
    private String password;
    private Role role;


    public CustomUserDetails(User user) {
        this.id=user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority(role.getName()));
        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
