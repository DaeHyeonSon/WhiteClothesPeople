//package com.whitepeoples.wooso.model.entity;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.Data;
//
//@Entity
//@Table(name = "guarantor")
//@Data
//public class Guarantor {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer guarantorId;
//
//    private String name;
//    private Boolean verificationStatus;
//    private String guaranteeDocument;
//    private String phoneNumber;
//    private String gender;
//    private Integer accountNumber;
//    private String bank;
//
//    @ManyToOne
//    @JoinColumn(name = "user_profile")
//    private Profile userProfile;
//
//    // Getters and Setters
//}
//
package com.whitepeoples.wooso.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "guarantor")
@Data
public class Guarantor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer guarantorId;

    private String name;
    private Boolean verificationStatus;
    private String guaranteeDocument;
    private String phoneNumber;
    private String gender;
    private Integer accountNumber;
    private String bank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile")
    private Profile userProfile;
}
