package com.whitepeoples.wooso.model.entity.relations;


import java.time.LocalDateTime;

import com.whitepeoples.wooso.model.entity.Guarantor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "guarantor_guarantor_relation")
public class GuarantorGuarantorRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer relationId;

    @ManyToOne
    @JoinColumn(name = "follower_guarantor_id")
    private Guarantor followerGuarantor;

    @ManyToOne
    @JoinColumn(name = "following_guarantor_id")
    private Guarantor followingGuarantor;

    private String status;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;
}