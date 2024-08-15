package com.whitepeoples.wooso.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.whitepeoples.wooso.dao.ReviewRepository;
import com.whitepeoples.wooso.dao.UserRepository;
import com.whitepeoples.wooso.model.dto.ReviewDTO;
import com.whitepeoples.wooso.model.entity.Review;
import com.whitepeoples.wooso.model.entity.User;

import jakarta.transaction.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewRepository reviewDAO;

	@Autowired
	private UserRepository userRepository;
	private ModelMapper mapper = new ModelMapper();
	
	
    public ReviewServiceImpl() {
        // Configure ModelMapper mappings
        mapper.addMappings(new PropertyMap<Review, ReviewDTO>() {
            @Override
            protected void configure() {
            	// map은 ReviewDTO 
            	// source은 Review 이렇게 구성된듯 
            	//DTO와 필드명은 같으나 타입이 다를 경우 아래처럼 
            	//
            	//map().setMatchId -> ReviewDTO.setMatchId() 입니다. 
            	//source.getMatch().getMatchId() -> Review.getMatch().getMatchId() 입니다
            	
                map().setMatchId(source.getMatch().getMatchId()); // Map Matching ID to ReviewDTO
                map().setReviewer(source.getReviewer().getUserId());  // Map Reviewer ID to ReviewDTO
            }
        });
    }

	// 주선자와 매핑된 리뷰 1개 조회
	@Override
	public ReviewDTO readOneReview(int id) {
		Optional<Review> entity = reviewDAO.findById(id);
		ReviewDTO reviewDto = mapper.map(entity, ReviewDTO.class);
		return reviewDto;
	}
	
	// 주선자와 매핑된 모든 리뷰 조회
	@Override
	public List<ReviewDTO> readAllReview(Integer reviewer) throws Exception {
		Optional<User> oUser = userRepository.findById(reviewer);
		if(!oUser.isPresent()) throw new Exception();
		User user = oUser.get();
		List<Review> entityList = reviewDAO.findByReviewer(user);
		return entityList.stream().map(entity -> mapper.map(entity, ReviewDTO.class)).collect(Collectors.toList());
	}

	// 리뷰 작성
	@Override
	@Transactional
	public boolean addReview(Review review) throws Exception {
		Review rv = reviewDAO.save(review);
		if (rv != null) {
			return true;
		} else
			throw new Exception("리뷰 등록에 실패했습니다.");

	}

}
