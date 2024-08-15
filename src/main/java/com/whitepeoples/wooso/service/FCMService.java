package com.whitepeoples.wooso.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public interface FCMService {
	 public void initialize() throws IOException;
	 public void sendNotification(String token, String title, String body);
	 
	 
	 
}
