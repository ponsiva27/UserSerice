package com.example.userservice.SecurityPackage.Service;

import com.example.userservice.Model.User;
import com.example.userservice.Repository.UserRepository;
import com.example.userservice.SecurityPackage.Models.CustomUserDetails;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class CustomUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository){

        this.userRepository =userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      Optional<User> userOptional =  userRepository.findByEmailId(username);
       if(userOptional.isEmpty()){
           throw new UsernameNotFoundException("User with Email Id "+ username+" "+ "doesn't exist");

       }
       User user = userOptional.get();   // This Interface "UserDetailsService" implements userDetails so we need to define our own userdetails
       UserDetails userDetails = new CustomUserDetails(user); //hence ,we created customuserdetails and passed the user object as paramenter
        return  userDetails;                                // get back the customized user details as a response
    }
}
