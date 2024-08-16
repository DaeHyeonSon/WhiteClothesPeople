package com.whitepeoples.wooso.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.whitepeoples.wooso.model.dto.ReviewDTO;
import com.whitepeoples.wooso.model.entity.Review;

@Service
public interface ReviewService {

	public ReviewDTO readOneReview(int id);

	public List<ReviewDTO> readAllReview(Integer reviewerId) throws Exception;

	public boolean addReview(Review review) throws Exception;



}
