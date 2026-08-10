package com.example.ecommerceapi.modal;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Collections.EMPTY_SET),

    CUSTOMER(Set.of(
            Permission.CUSTOMER_READ,
            Permission.CUSTOMER_SEARCH
    )),

    ADMIN(Set.of(
            Permission.ADMIN_READ,
            Permission.ADMIN_UPDATE,
            Permission.ADMIN_DELETE,
            Permission.ADMIN_SEARCH
    ));

    private final Set<Permission> permission;

    Role(Set<Permission> permission){
        this.permission = permission;
    }

    public List<SimpleGrantedAuthority> autherities(){
        List<SimpleGrantedAuthority> baby = new ArrayList<>();
        for(Permission p : permission){
            baby.add(new SimpleGrantedAuthority(p.getpermission()));
        }

        baby.add(new SimpleGrantedAuthority("ROLE_"+this.name()));

        return baby;
    }

}
