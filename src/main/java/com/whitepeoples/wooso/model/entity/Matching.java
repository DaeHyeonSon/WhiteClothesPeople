package com.whitepeoples.wooso.model.entity;

import java.security.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "matching")
@Data
public class Matching {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer matchId;

	    @ManyToOne
	    @JoinColumn(name = "request_matchmaker_id")
	    private Guarantor requestMatchmaker;

	    @ManyToOne
	    @JoinColumn(name = "response_matchmaker_id")
	    private Guarantor responseMatchmaker;

	    @ManyToOne
	    @JoinColumn(name = "request_user_id")
	    private User requestUser;

	    @ManyToOne
	    @JoinColumn(name = "response_user_id")
	    private User responseUser;

	    private String matchType;
	    private String matchStatus;
	    private Timestamp createdAt;

    // Getters and Setters
}

