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
@Table(name = "Matching")
public class Matching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matchId;

    @ManyToOne
    @JoinColumn(name = "matchmaker_id")
    private Guarantor matchmaker;

    @ManyToOne
    @JoinColumn(name = "female_id")
    private User female;

    @ManyToOne
    @JoinColumn(name = "male_id")
    private User male;

    private String matchType;
    private String matchStatus;
    private Timestamp createdAt;

    // Getters and Setters
}

