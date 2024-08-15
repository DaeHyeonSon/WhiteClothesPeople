package com.whitepeoples.wooso.model.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.whitepeoples.wooso.model.entity.EnumTypes.PlanType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "subscription")
@Data
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private Integer subscriptionId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "plan", length = 50)
    private PlanType plan;

    @Column(name = "price")
    private Integer price;

    @Column(name = "remaining_matches")
    private Integer remainingMatches;

    @Column(name = "merchant_uid")
    private String merchant_uid;
    
    @Column(name = "next_pay_date")
    private LocalDate nextPayDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    public void onPrePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        // 선택한 플랜의 가격을 자동으로 설정
        if (this.plan != null) {
            this.price = this.plan.getPrice();
        }
    }

    // 엔티티가 업데이트될 때 호출되는 메소드
    @PreUpdate
    public void onPreUpdate() {
        this.updatedAt = LocalDateTime.now();

        // 선택한 플랜의 가격을 자동으로 설정
        if (this.plan != null) {
            this.price = this.plan.getPrice();
        }
    }
}