package com.whitepeoples.wooso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whitepeoples.wooso.dao.GuarantorRepository;
import com.whitepeoples.wooso.dao.MatchingRepository;
import com.whitepeoples.wooso.dao.ProfileRepository;
import com.whitepeoples.wooso.dao.ReviewRepository;
import com.whitepeoples.wooso.dao.SubscriptionRepository;
import com.whitepeoples.wooso.dao.UserRepository;
import com.whitepeoples.wooso.model.entity.Guarantor;
import com.whitepeoples.wooso.model.entity.Matching;
import com.whitepeoples.wooso.model.entity.Profile;
import com.whitepeoples.wooso.model.entity.Review;
import com.whitepeoples.wooso.model.entity.Subscription;
import com.whitepeoples.wooso.model.entity.User;

import jakarta.transaction.Transactional;

import java.util.Optional;

@Controller
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private GuarantorRepository guarantorRepository;

    @Autowired
    private MatchingRepository matchingRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @GetMapping("/test")
    @Transactional
    public String test() {
        // User Repository 테스트
//        System.out.println("=== User Repository 테스트 ===");
//        Optional<User> user = userRepository.findByUsername("user1");
//        user.ifPresent(System.out::println);
//
//        userRepository.deleteByUsername("user2");
//        System.out.println("Deleted user with username 'user2'");
//
//        // Profile Repository 테스트
//        System.out.println("=== Profile Repository 테스트 ===");
//        Optional<Profile> profile = profileRepository.findById(1);
//        profile.ifPresent(System.out::println);
//
//        // Guarantor Repository 테스트
//        System.out.println("=== Guarantor Repository 테스트 ===");
//        Optional<Guarantor> guarantor = guarantorRepository.findByGuarantorId(1);
//        guarantor.ifPresent(System.out::println);
//
//        // Matching Repository 테스트
//        System.out.println("=== Matching Repository 테스트 ===");
//        Optional<Matching> matching = matchingRepository.findByMatchId(1);
//        matching.ifPresent(System.out::println);
//
//        // Review Repository 테스트
//        System.out.println("=== Review Repository 테스트 ===");
//        Optional<Review> review = reviewRepository.findByReviewId(1);
//        review.ifPresent(System.out::println);
//
//        // Subscription Repository 테스트
//        System.out.println("=== Subscription Repository 테스트 ===");
//        Optional<Subscription> subscription = subscriptionRepository.findBySubscriptionId(1);
//        subscription.ifPresent(System.out::println);

        // 페이지 네이션 테스트
        System.out.println("=== 페이지 네이션 테스트 ===");
        
        Pageable pageable = PageRequest.of(0 , 3); // 0번 페이지, 2개의 항목
        Page<User> userPage = userRepository.findAll(pageable);
        userPage.forEach(System.out::println);

        Page<Guarantor> guarantorPage = guarantorRepository.findAll(pageable);
        guarantorPage.forEach(System.out::println);

        Page<Matching> matchingPage = matchingRepository.findAll(pageable);
        matchingPage.forEach(System.out::println);

        Page<Review> reviewPage = reviewRepository.findAll(pageable);
        reviewPage.forEach(System.out::println);

        Page<Subscription> subscriptionPage = subscriptionRepository.findAll(pageable);
        subscriptionPage.forEach(System.out::println);

        return "payment";
    }
}