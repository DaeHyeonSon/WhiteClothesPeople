package com.whitepeoples.wooso.model.entity;

import java.security.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Matching match;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private User reviewer;

    private Integer rating;
    private String reviewDescription;
    private Timestamp createdAt;

    // Getters and Setters
}

