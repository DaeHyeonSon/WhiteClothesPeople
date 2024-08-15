package com.whitepeoples.wooso.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.whitepeoples.wooso.model.entity.Subscription;
import com.whitepeoples.wooso.model.entity.User;
import com.whitepeoples.wooso.model.entity.EnumTypes.PlanType;

@Service
public interface SubscriptionService {
	Subscription createSubscription(Integer userId, PlanType plan, String merchantId);
	boolean canCancelSubscription(Optional<Subscription> currentSubscription);
	void cancelSubscription(Optional<Subscription> currentSubscription);
	Optional<Subscription> findByUser(User user); 
}