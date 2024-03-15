package com.example.userservice.DTO;

import com.example.userservice.Model.Role;
import com.example.userservice.Model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {

    private long userId;
    private String name;
    private String email;
    private List<Role> roles;


    public static UserDTO from(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.email = user.getEmailId();
        userDTO.name = user.getName();
        userDTO.roles = user.getRoles();
        userDTO.userId = user.getId();
        return userDTO;
    }
}
