package com.whitepeoples.wooso.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whitepeoples.wooso.model.entity.Review;
import com.whitepeoples.wooso.model.entity.User;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

	// 전체 조회
	List<Review> findByReviewer(User reviewer);

	// 단일 조회
	Optional<Review> findByReviewId(Integer id);

	// 필드를 통한 조회
	Optional<Review> findByRating(Integer rating);

	// 필드를 통한 삭제
	// void deleteByRating(Integer rating);
}
