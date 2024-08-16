
package com.whitepeoples.wooso.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

import com.whitepeoples.wooso.model.entity.EnumTypes.MatchingRequestType;

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
    @Enumerated(EnumType.STRING)
    private MatchingRequestType status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}

