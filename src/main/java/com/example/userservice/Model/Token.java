package com.example.userservice.Model;

import jakarta.persistence.Entity;

import jakarta.persistence.ManyToOne;

import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
public class Token extends BaseModel {

    private String tokenValue;
    private Date expiredAt;
    @ManyToOne
    private User user;


}
