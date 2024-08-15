package com.whitepeoples.wooso.model.entity.relations;


import java.time.LocalDateTime;

import com.whitepeoples.wooso.model.entity.Guarantor;
import com.whitepeoples.wooso.model.entity.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "guarantor_user_relation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuarantorUserRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer relationId;

    @ManyToOne
    @JoinColumn(name = "follower_guarantor_id")
    private Guarantor followerGuarantor;

    @ManyToOne
    @JoinColumn(name = "following_user_id")
    private User followingUser;

    private String status;

    @Column(name = "follower_type")
    private String followerType;

    @Column(name = "follower_id")
    private Integer followerId;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;
}
