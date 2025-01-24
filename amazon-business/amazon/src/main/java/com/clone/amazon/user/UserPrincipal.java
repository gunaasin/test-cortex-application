package com.clone.amazon.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private final AmazonUser amazonUser;

    public UserPrincipal(AmazonUser amazonUser){
        this.amazonUser=amazonUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(amazonUser.getRole()));
    }

    @Override
    public String getPassword() {
        return amazonUser.getPassword();
    }


    public String getEmail(){  // polymorphism worked hear
        return amazonUser.getEmail();
    }

    @Override
    public String getUsername() {
        return amazonUser.getEmail();
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
