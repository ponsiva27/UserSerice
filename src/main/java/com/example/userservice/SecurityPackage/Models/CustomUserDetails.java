package com.example.userservice.SecurityPackage.Models;

import com.example.userservice.Model.Role;
import com.example.userservice.Model.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonDeserialize
public class CustomUserDetails implements UserDetails {

    private User user;
    private String password;
    private String username;
    private boolean accountNonExpired;

    private boolean credentialsNonExpired;

    private boolean  accountNonLocked;
    private boolean enabled;
    
    private List<CustomGrantedAuthority> authorities;


    private String isCredentialsNonExpired;

    public CustomUserDetails() {

    }

    public CustomUserDetails(User user){

        this.user =user;
        this.username = user.getEmailId();
        this.password = user.getBCryptPassword();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.enabled = true;
        this.credentialsNonExpired = true;

        List<CustomGrantedAuthority> AuthorityList = new ArrayList<>();
        for(Role roles : user.getRoles()) {
            CustomGrantedAuthority  customGrantedAuthority = new CustomGrantedAuthority(roles);
            AuthorityList.add(customGrantedAuthority);
        }
        
        this.authorities = AuthorityList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //I have to return  list of <something which is like a granted authority>
        return  authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        //google maintains your login request for 3 months after which it will get expired.
        //But in my case, I will have it not expired. so i make it as true
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        //This  method will lock the account if the user made too many failed login attempts.
        //But i will not mark it lock. so making this as true.
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //user credentials expire
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        //user account deactivated or activated.
        //In my case , i will make is enabled so given true.
        return enabled;
    }
}
