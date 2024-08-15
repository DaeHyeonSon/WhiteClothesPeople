package com.whitepeoples.wooso.model.dto;

import com.whitepeoples.wooso.model.entity.EnumTypes.UserType;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String email;
    private String password;
    private UserType userType;
}