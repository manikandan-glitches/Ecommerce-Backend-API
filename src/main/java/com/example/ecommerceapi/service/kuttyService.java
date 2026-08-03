package com.example.ecommerceapi.service;

import com.example.ecommerceapi.modal.User;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class kuttyService implements UserDetails{

    User user;
    public kuttyService(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPazzword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
}
