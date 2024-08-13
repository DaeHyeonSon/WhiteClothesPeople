package com.whitepeoples.wooso.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.whitepeoples.wooso.model.entity.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    // 범위 조회
    Page<Subscription> findAll(Pageable pageable);

    // 단일 조회
    Optional<Subscription> findBySubscriptionId(Integer id);

    // 필드를 통한 조회
    Optional<Subscription> findByPlan(String plan);

    // 필드를 통한 삭제
//    void deleteByPlan(String plan);

}