
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

