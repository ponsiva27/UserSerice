package com.example.userservice.Controller;

import com.example.userservice.DTO.Enum.LoginStatus;
import com.example.userservice.DTO.LoginRequestDTO;
import com.example.userservice.DTO.LoginResponseDTO;
import com.example.userservice.DTO.LogoutRequestDTO;
import com.example.userservice.DTO.SignupRequestDTO;
import com.example.userservice.Exception.TokenInvalidException;
import com.example.userservice.Exception.UserExistException;
import com.example.userservice.Model.Token;
import com.example.userservice.Model.User;
import com.example.userservice.Repository.TokenRepository;
import com.example.userservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private final TokenRepository tokenRepository;

    @Autowired
    public UserController(UserService userService,
                          TokenRepository tokenRepository){
        this.userService = userService;
        this.tokenRepository = tokenRepository;
    }

    @PostMapping("/register")
    public User signUp(@RequestBody SignupRequestDTO signupRequestDTO) throws UserExistException {


           return  userService.signUp(
                   signupRequestDTO.getName(),
                   signupRequestDTO.getEmailId(),
                   signupRequestDTO.getPassword()
           );


    }

    @PostMapping("/login")
    public LoginResponseDTO  login(@RequestBody LoginRequestDTO requestDTO){

        LoginResponseDTO responseDTO = new LoginResponseDTO();

        try {
        Token token = userService.login(requestDTO.getEmailId(), requestDTO.getPassword());
            responseDTO.setTokenValue(token.getValue());
            responseDTO.setStatus(LoginStatus.LOGIN_SUCCESS);

        } catch ( Exception e ) {
            responseDTO.setStatus(LoginStatus.LOGIN_FAILS);
        }

        return responseDTO;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDTO logoutRequest) throws TokenInvalidException {
        Token token = userService.logout(logoutRequest.getTokenValue());
       ResponseEntity<Void> responseEntity = new ResponseEntity<>(
              token.isDeleted()==true ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR
       );
       return  responseEntity;
    }


    //user wants to execute, getAllProducts()
    //user will give a token, to get AllProducts.
    //product service will call the userService to validate the token

    @GetMapping("/validate/{tokenValue}")
    public Token validateToken(@PathVariable(name ="tokenValue") String tokenValue) {
        Token token = userService.validateToken(tokenValue);

        return token;
    }
}
