package com.whitepeoples.wooso.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "profile")
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer profileId;

    private Integer entityId;
    private String entityType;
    private Integer userAge;
    private String userMbti;
    private String userHobby;
    private String userAddress;
    private String userIncome;
    private String userImgUrl;
    private String userDescription;

    // Getters and Setters
}

