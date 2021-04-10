package com.iot.findneighbor.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iot.findneighbor.domain.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class UserPrincipal implements UserDetails {
    private Long id;

    private String name;

    private String username;

    private boolean pets;

    private boolean badHabits;

    private boolean kindOfActivity;

    private String sex;

    private String maritalStatus;

    private int age;

    private boolean haveJobOrJobless;

    private String phoneNumber;

    private String moreAboutUser;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;


    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new UserPrincipal(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.isPets(),
                user.isBadHabits(),
                user.isKindOfActivity(),
                user.getSex(),
                user.getMaritalStatus(),
                user.getAge(),
                user.isHaveJobOrJobless(),
                user.getPhoneNumber(),
                user.getMoreAboutUser(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }



    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
