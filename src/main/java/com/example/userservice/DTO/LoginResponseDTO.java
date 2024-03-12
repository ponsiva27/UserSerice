package com.example.userservice.DTO;

import com.example.userservice.DTO.Enum.LoginStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {

    private String tokenValue;

    private LoginStatus status;

}
