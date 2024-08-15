//package com.whitepeoples.wooso.model.entity;
//
//
//
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.Data;
//
//@Entity
//@Table(name = "review")
//@Data
//public class Review {
//		@Id
//	    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	    private Integer reviewId;
//
//	    @ManyToOne
//	    @JoinColumn(name = "match_id")
//	    private Matching match;
//
//	    @ManyToOne
//	    @JoinColumn(name = "reviewer_id")
//	    private User reviewer;
//
//	    private Integer rating;
//	    private String reviewDescription;
//	    private Timestamp createdAt;
//
//	    // Getters and Setters
//}
//

package com.whitepeoples.wooso.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "review")
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Matching match;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id")
    private User reviewer;

    private Integer rating;
    private String reviewDescription;
    private Timestamp createdAt;
}
