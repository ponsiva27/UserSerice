package com.example.userservice.SecurityPackage.Models;

import com.example.userservice.Model.Role;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;


@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {

    private Role  roles;

    private String authority;

    public CustomGrantedAuthority() {

    }

    public CustomGrantedAuthority(Role  roles){
        this.roles =roles;
        this.authority =  roles.getName();
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
