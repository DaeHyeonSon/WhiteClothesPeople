package com.whitepeoples.wooso.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whitepeoples.wooso.dao.SubscriptionRepository;
import com.whitepeoples.wooso.dao.UserRepository;
import com.whitepeoples.wooso.model.entity.Subscription;
import com.whitepeoples.wooso.model.entity.User;
import com.whitepeoples.wooso.model.entity.EnumTypes.PlanType;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
    private UserRepository userRepository;

	@Override
	public Subscription createSubscription(Integer userId, PlanType plan, String merchantId) {
		
		 User user = userRepository.findById(userId)
	                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자가 존재하지 않습니다: " + userId));

		Subscription subscription = new Subscription();
		subscription.setUser(user); // User 엔티티가 userId로 생성자를 가지고 있다고 가정
		subscription.setPlan(plan);
		subscription.setMerchant_uid(merchantId);
		if(plan==PlanType.BASIC) {
			subscription.setRemainingMatches(4);
		}else {
			subscription.setRemainingMatches(8);
		}
		subscription.setNextPayDate(LocalDate.now().plusMonths(1)); // 월간 구독 플랜 예시

		subscriptionRepository.save(subscription);
		return subscription;
	}

	 @Override
	    public boolean canCancelSubscription(Optional<Subscription> subscription) {
	        return subscription.map(sub -> {
	            LocalDateTime now = LocalDateTime.now();
	            LocalDateTime subscriptionDate = sub.getCreatedAt();

	            boolean withinThreeDays = now.isBefore(subscriptionDate.plusDays(3));
	            PlanType type = sub.getPlan();
	            boolean noMatchesUsed;
	            if(type==PlanType.BASIC) {
	            	noMatchesUsed = sub.getRemainingMatches() == 4;
	            }else {
	            	noMatchesUsed = sub.getRemainingMatches() == 8;
	            }
	            
	            return withinThreeDays && noMatchesUsed;
	        }).orElse(false);
	    }

	@Override
	public void cancelSubscription(Optional<Subscription> subscription) {
		subscription.ifPresent(subscriptionRepository::delete);
	}
	
	@Override
	public Optional<Subscription> findByUser(User user) {
	    return Optional.ofNullable(subscriptionRepository.findByUser(user));
	}

}