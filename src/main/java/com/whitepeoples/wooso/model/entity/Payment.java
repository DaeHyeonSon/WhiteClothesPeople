//package com.whitepeoples.wooso.model.entity;
//
//import java.sql.Date;
//import java.sql.Timestamp;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.PrePersist;
//import jakarta.persistence.PreUpdate;
//import jakarta.persistence.Table;
//import lombok.Data;
//
//@Entity
//@Table(name = "subpayment")
//@Data
//public class Payment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "payment_id")
//    private Integer paymentId;
//
//    @ManyToOne
//    @JoinColumn(name = "subscription_id", nullable = false)
//    private Subscription subscription;
//
//    @Column(name = "payment_date")
//    private LocalDate paymentDate;
//
//    @Column(name = "amount")
//    private Integer amount;
//
//    @Column(name = "merchant_id", length = 50)
//    private String merchantId;
//
//    @Column(name = "is_refundable")
//    private Boolean isRefundable;
//
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
//    
//    @PrePersist
//    public void onPrePersist() {
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//        this.isRefundable = true; // 기본값을 true로 설정
//    }
//
//    @PreUpdate
//    public void onPreUpdate() {
//        this.updatedAt = LocalDateTime.now();
//
//        // 결제일로부터 3일이 지났다면 isRefundable을 false로 설정
//        if (this.paymentDate != null && LocalDate.now().isAfter(this.paymentDate.plusDays(3))) {
//            this.isRefundable = false;
//        }
//    }
//}


package com.whitepeoples.wooso.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.sql.Date;

@Entity
@Table(name = "payment")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    private Date paymentDate;
    private Integer amount;
    private String merchantId;
    private Boolean isRefundable;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
