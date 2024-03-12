package com.example.userservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDTO {

    private String name;
    private String emailId;
    private String password;

}
