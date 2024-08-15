package com.whitepeoples.wooso.model.dto;

import lombok.Data;

@Data
public class UserProfileDTO {
    private String username;
    private String email;
    private String description;
    private String mbti;
    private String hobby;
    private Integer age;
}
