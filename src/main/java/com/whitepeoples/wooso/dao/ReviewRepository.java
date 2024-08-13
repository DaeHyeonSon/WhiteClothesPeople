package com.whitepeoples.wooso.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.whitepeoples.wooso.model.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    // 범위 조회
    Page<Review> findAll(Pageable pageable);

    // 단일 조회
    Optional<Review> findByReviewId(Integer id);

    // 필드를 통한 조회
    Optional<Review> findByRating(Integer rating);

    // 필드를 통한 삭제
   // void deleteByRating(Integer rating);
}
