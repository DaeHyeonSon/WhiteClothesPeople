package com.whitepeoples.wooso.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.whitepeoples.wooso.dao.UserRepository;
import com.whitepeoples.wooso.model.entity.Subscription;
import com.whitepeoples.wooso.model.entity.User;
import com.whitepeoples.wooso.model.entity.EnumTypes.PlanType;
import com.whitepeoples.wooso.service.SubscriptionService;

@Controller
public class SubscriptionController {
	
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserRepository userRepository;

    // 구독 신청
    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestParam("userId") Integer userId, 
                                             @RequestParam("plan") PlanType plan) {
        // userId를 사용하여 User 객체를 가져옴
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자가 존재하지 않습니다: " + userId));

        // 현재 구독하고 있는 구독제가 있는지 확인
        Optional<Subscription> currentSubscription = subscriptionService.findByUser(user);

        if (currentSubscription.isPresent()) {
        	logger.info("Already Subscribed: user={}, subscription={}", user, currentSubscription);
            return ResponseEntity.badRequest().body("현재 구독하고 있는 서비스가 있습니다.");
        }

        // 새로운 구독 생성
        Subscription sub = subscriptionService.createSubscription(userId, plan);
        logger.info("Newly Created Subscritpion: user={}, subscription={}", user.getUserId(), sub.getSubscriptionId());
        return ResponseEntity.ok("구독이 성공적으로 신청되었습니다.");
    }


    // 구독 취소
    @PostMapping("/unsubscribe")
    public ResponseEntity<String> unsubscribe(@RequestParam("userId") Integer userId) {
        // userId를 사용하여 User 객체를 가져옴
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자가 존재하지 않습니다: " + userId));

        // 현재 구독을 가져옴
        Optional<Subscription> currentSubscription = subscriptionService.findByUser(user);

        if (currentSubscription == null) {
        	logger.info("No Current Subscription}");
            return ResponseEntity.badRequest().body("구독중인 서비스가 없습니다.");
        }

        // 사용자가 구독한 날짜로부터 3일이 지나지 않았고, 매칭 횟수가 그대로라면 구독 취소가 가능하다.
        if (subscriptionService.canCancelSubscription(currentSubscription)) {
            subscriptionService.cancelSubscription(currentSubscription);
            logger.info("구독이 성공적으로 취소되었습니다.");
            return ResponseEntity.ok("구독이 성공적으로 취소되었습니다.");
        } else {
        	logger.info("구독 취소 요건을 충족하지 않습니다.");
            return ResponseEntity.badRequest().body("구독 취소 요건을 충족하지 않습니다.");
        }
    }
}
