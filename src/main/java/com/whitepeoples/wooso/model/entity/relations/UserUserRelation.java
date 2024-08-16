package com.whitepeoples.wooso.model.entity.relations;

//
//import java.time.LocalDateTime;
//
//import com.whitepeoples.wooso.model.entity.Guarantor;
//import com.whitepeoples.wooso.model.entity.User;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Entity
//@Table(name = "user_user_relation")
//public class UserUserRelation {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer relationId;
//
//    @ManyToOne
//    @JoinColumn(name = "follower_user_id")
//    private User followerUser;
//
//    @ManyToOne
//    @JoinColumn(name = "following_user_id")
//    private User followingUser;
//
//    private String status;
//
//    @Column(name = "created_at", updatable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    private LocalDateTime createdAt;
//
//    @Column(name = "updated_at")
//    @Temporal(TemporalType.TIMESTAMP)
//    private LocalDateTime updatedAt;
//}

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

import com.whitepeoples.wooso.model.entity.User;

@Entity
@Table(name = "user_user_relation")
@Data
public class UserUserRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer relationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_user_id")
    private User followerUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_user_id")
    private User followingUser;

    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}