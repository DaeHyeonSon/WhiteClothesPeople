//package com.whitepeoples.wooso.model.entity;
//
//
//import java.time.LocalDateTime;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.Builder;
//
//@Builder
//@Entity
//@Table(name = "request_matching")
//public class RequestMatching {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "request_id")
//    private Integer requestId;
//
//    @ManyToOne
//    @JoinColumn(name = "requester_id", nullable = true)
//    private User requester;
//
//    @ManyToOne
//    @JoinColumn(name = "guarantor_id", nullable = true)
//    private Guarantor guarantor;
//
//    @Column(name = "mandatory", length = 255)
//    private String mandatory;
//
//    @Column(name = "description", length = 255)
//    private String description;
//
//    @Column(name = "status", length = 50)
//    private String status;
//
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//    
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
//}

package com.whitepeoples.wooso.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "request_matching")
@Data
@Builder
public class RequestMatching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id")
    private User requester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guarantor_id")
    private Guarantor guarantor;

    private String mandatory;
    private String description;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}

