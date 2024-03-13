package com.example.userservice.Exception;

public class InvalidPasswordException extends  Exception {

    public InvalidPasswordException(String message){
         super(message);
    }
}
