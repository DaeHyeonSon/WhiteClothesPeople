package com.whitepeoples.wooso.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whitepeoples.wooso.dao.UserRepository;
import com.whitepeoples.wooso.model.entity.Subscription;
import com.whitepeoples.wooso.model.entity.User;
import com.whitepeoples.wooso.model.entity.EnumTypes.PlanType;
import com.whitepeoples.wooso.service.SubscriptionService;

@Controller
@RequestMapping("/subscription")
public class SubscriptionController {
	
    @Value("${imp.api.key}")
    private String apiKey;

    @Value("${imp.api.secretkey}")
    private String secretKey;

    @Value("${imp.init}")
    private String impCode;

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserRepository userRepository;

    // 구독 view
    @GetMapping("")
    public String subscriptionView(Model model) {
        logger.info("subscriptionView() ----");
        model.addAttribute("impCode", impCode);
        return "subscribe";
    }

    // 사용자 정보 가져오기
    @GetMapping("/details")
    @ResponseBody
    public Map<String, Object> getUserDetails() {
    	Integer userId = 1;
    	 User user = userRepository.findById(userId)
                 .orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자가 존재하지 않습니다: " + userId));
        Map<String, Object> dt = new HashMap<>();
        dt.put("userId", user.getUserId()); 
        Optional<Subscription> currentSubscription = subscriptionService.findByUser(user);
        if(currentSubscription.isPresent()) {
        	dt.put("merchantId", currentSubscription.get().getMerchant_uid());
        }
        logger.info("User details: {}", dt);
        return dt;
    }

    // 구독 여부 확인
    @PostMapping("/check-subscription")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> checkSubscription(@RequestParam("userId") Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자가 존재하지 않습니다: " + userId));

        Optional<Subscription> currentSubscription = subscriptionService.findByUser(user);
        Map<String, Object> response = new HashMap<>();

        if (currentSubscription.isPresent()) {
            Subscription subscription = currentSubscription.get();
            logger.info("User {} is already subscribed.", userId);
            response.put("isSubscribed", true);
            response.put("subscriptionName", subscription.getPlan().name());
            response.put("renewalDate", subscription.getNextPayDate().toString());
        } else {
            logger.info("User {} is not subscribed.", userId);
            response.put("isSubscribed", false);
        }

        return ResponseEntity.ok(response);
    }

    // 구독 신청
    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestParam("userId") Integer userId,
                                            @RequestParam("plan") PlanType plan,
                                            @RequestParam("merchant_uid") String merchantId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자가 존재하지 않습니다: " + userId));

        Subscription newSubscription = subscriptionService.createSubscription(userId, plan, merchantId);
        logger.info("New subscription created: User={}, Subscription={}", userId, newSubscription.getSubscriptionId());

        return ResponseEntity.ok("구독이 성공적으로 신청되었습니다.");
    }
    
    @GetMapping("/currentStatus")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> subscribeStatus(@RequestParam("userId") Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자가 존재하지 않습니다: " + userId));

        Optional<Subscription> currentSubscription = subscriptionService.findByUser(user);

        Map<String, Object> response = new HashMap<>();
        if (currentSubscription.isPresent()) {
            Subscription subscription = currentSubscription.get();
            response.put("subscriptionName", subscription.getPlan().name());  // Assuming `getPlanType()` returns an Enum with a `name()` method
            response.put("renewalDate", subscription.getNextPayDate().toString());  // Adjust if `getRenewalDate()` returns a date format you need
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "No subscription found.");
            return ResponseEntity.ok(response);
        }
    }

    // 구독 취소
    @PostMapping("/unsubscribe")
    public ResponseEntity<String> unsubscribe(@RequestParam("userId") Integer userId) {
        // 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자가 존재하지 않습니다: " + userId));

        // 현재 구독 상태 조회
        Optional<Subscription> currentSubscription = subscriptionService.findByUser(user);

        // 구독이 존재하지 않으면 응답
        if (!currentSubscription.isPresent()) {
            logger.info("No subscription found for user {}", userId);
            return ResponseEntity.badRequest().body("구독중인 서비스가 없습니다.");
        }

        // 구독 취소 가능 여부 확인
        if (!subscriptionService.canCancelSubscription(currentSubscription)) {
            logger.info("Subscription cancellation criteria not met for user {}", userId);
            return ResponseEntity.badRequest().body("구독 취소 기준을 충족하지 않습니다.");
        }

        // 구독 취소
        subscriptionService.cancelSubscription(currentSubscription);
        logger.info("Subscription cancelled for user {}", userId);

        return ResponseEntity.ok("구독이 성공적으로 취소되었습니다.");
    }

}
