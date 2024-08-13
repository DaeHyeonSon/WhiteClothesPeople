package com.whitepeoples.wooso.model.entity;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subscriptionId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String plan;
    private Integer remainingMatches;
    private Date nextPayDate;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Getters and Setters
}

