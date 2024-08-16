package com.whitepeoples.wooso.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface IncentiveService {
	public Map<String, Object> calculateMatchingAndSendIncentive(Integer guarantorId);
}