package com.whitepeoples.wooso.model.entity;

import java.time.LocalDateTime;

import com.whitepeoples.wooso.model.entity.EnumTypes.MatchingRequestType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "request_matching")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
