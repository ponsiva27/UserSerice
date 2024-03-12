package com.example.userservice.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserExistException extends  Exception{

    public UserExistException(String message){
        super(message);
    }
}
