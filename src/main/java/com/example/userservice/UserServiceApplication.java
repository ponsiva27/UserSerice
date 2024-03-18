package com.example.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(UserServiceApplication.class, args);
    }
    //1. I want to use my DB for maintaining User.
    //2. I want to add some customize data to the token.
    //3 .I want third party API to talk to my user service.

}
