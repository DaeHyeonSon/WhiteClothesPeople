package com.whitepeoples.wooso.model.dto;

import lombok.Data;

@Data
public class UserProfileDTO {
    private String username;
    private String email;
    private String description;
    private String mbti;
    private String hobbies;
    private String address;
    private Integer age;
    private String income;
    private String userImgUrl;
}


