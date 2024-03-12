package com.example.userservice.Controller;


import com.example.userservice.DTO.Enum.SignUpStatus;
import com.example.userservice.DTO.LoginRequestDTO;
import com.example.userservice.DTO.LoginResponseDTO;
import com.example.userservice.DTO.SignupRequestDTO;
import com.example.userservice.DTO.SignupResponseDTO;
import com.example.userservice.Exception.UserExistException;
import com.example.userservice.Model.User;
import com.example.userservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
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
    public LoginResponseDTO  login(LoginRequestDTO loginRequestDTO){
        return null;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return  null;
    }
}
