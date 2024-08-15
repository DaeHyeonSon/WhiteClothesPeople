package com.whitepeoples.wooso.model.entity;

import com.whitepeoples.wooso.model.entity.EnumTypes.UserType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "profile")
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer profileId;

    private Integer entityId;
    
    @Enumerated(EnumType.STRING)
    private UserType entityType;
    private Integer userAge;
    private String userMbti;
    private String userHobby;
    private String userAddress;
    private String userIncome;
    private String userImgUrl;
    private String userDescription;

    // Getters and Setters
}

