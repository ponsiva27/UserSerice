package com.example.userservice.Service;

import com.example.userservice.Exception.UserExistException;
import com.example.userservice.Model.User;
import com.example.userservice.Repository.UserRepository;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private  BCryptPasswordEncoder bCryptPasswordEncoder;
    private  UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public User signUp(String name, String email, String password) throws UserExistException {

        Optional<User> optionalUser = userRepository.findByEmailId(email);

        if(optionalUser.isPresent()){
            throw new UserExistException("User Already Exist , Please login with email Id"+ email );
        }

        User user = new User();
       user.setName(name);
       user.setEmailId(email);

       user.setBCryptPassword(bCryptPasswordEncoder.encode(password));

       User registeredUser =  userRepository.save(user);

       return registeredUser;
    }
}
