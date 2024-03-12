package com.example.userservice.DTO;

import com.example.userservice.DTO.Enum.SignUpStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponseDTO {

    private SignUpStatus status;

    private long id;


}
