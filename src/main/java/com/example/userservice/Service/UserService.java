package com.example.userservice.Service;

import com.example.userservice.Exception.InvalidPasswordException;
import com.example.userservice.Exception.TokenInvalidException;
import com.example.userservice.Exception.UserExistException;
import com.example.userservice.Exception.UserNotFoundException;
import com.example.userservice.Model.Token;
import com.example.userservice.Model.User;
import com.example.userservice.Repository.TokenRepository;
import com.example.userservice.Repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {


    private  BCryptPasswordEncoder bCryptPasswordEncoder;
    private  UserRepository userRepository;

    private TokenRepository tokenRepository;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                                TokenRepository tokenRepository){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
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


    public Token login(String email, String password) throws UserNotFoundException, InvalidPasswordException {

       Optional<User> userOptional =  userRepository.findByEmailId(email);

        if(userOptional.isEmpty()) {
            throw new UserNotFoundException("Given Email Id"+" "+email+" "+"doesn't exist"+ "Please Sign Up");
        }

        User savedUser = userOptional.get();

        if(!bCryptPasswordEncoder.matches(password, savedUser.getBCryptPassword())) {
             throw new InvalidPasswordException("Please Enter Correct Password");
        }

        //If both email and password are correct. then Generate a Token.

        Token token = new Token();
        token.setUser(savedUser);

        //Expiry date is 30 days from the time token generated.
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 15);

        java.util.Date expirationDate = cal.getTime();

        token.setExpiredAt(expirationDate);

        //token value
        String vals =RandomStringUtils.randomAlphanumeric(30);
        token.setValue(vals);

        Token savedToken =tokenRepository.save(token);

        return savedToken;
    }

    public Token logout(String tokenValue) throws TokenInvalidException {
       Optional<Token> tokenOptional =  tokenRepository.findTokenByValueAndExpiredAtGreaterThanAndIsDeleted(tokenValue, new Date(), false);

       if(tokenOptional.isEmpty()){
           throw new TokenInvalidException("Token is Invalid or Expired");
       }

       Token token = tokenOptional.get(); //get the token object.
        token.setDeleted(true);     //mark the token deleted column as "True"
      Token savedToken = tokenRepository.save(token);   //save to DB again  and return the call to success.

      return savedToken;

    }
    public Token validateToken(String tokenValue){
        Optional<Token> tokenOptional =  tokenRepository.findTokenByValueAndExpiredAtGreaterThanAndIsDeleted(tokenValue, new Date(), false);

        if(tokenOptional.isEmpty()){
            return  null;
        }

        return tokenOptional.get();
    }

}
