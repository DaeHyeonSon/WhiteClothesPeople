package com.whitepeoples.wooso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.whitepeoples.wooso.model.dto.ReviewDTO;
import com.whitepeoples.wooso.model.entity.Review;
import com.whitepeoples.wooso.service.ReviewService;

@RestController
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	// 리뷰 1개 조회
	@PostMapping("review")
	public ReviewDTO readOneReview(@RequestBody int id) {
		System.out.println("리뷰 1개 : " + reviewService.readOneReview(id));
		return reviewService.readOneReview(id);
	}

    // 특정 주선자 ID로 모든 리뷰 조회
    @GetMapping("reviews")
    public List<ReviewDTO> readAllReview(@RequestParam("reviewerId") Integer reviewerId) throws Exception {
        List<ReviewDTO> rev = reviewService.readAllReview(reviewerId);
        System.out.println("리뷰 전체 : " + rev);
        return rev;
    }

	// 리뷰 작성
	@PostMapping("addReview")
	public String addReview(@RequestParam("review") Review review) throws Exception {
		boolean result = reviewService.addReview(review);
		if(result == true) {
			System.out.println("리뷰 작성 성공");
			return "리뷰 작성 성공";
		}else {
			System.out.println("리뷰 작성 실패");
			return "리뷰 작성 실패";
		}
	}
}