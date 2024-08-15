package com.whitepeoples.wooso.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.whitepeoples.wooso.model.entity.User;

@Service
public interface UserService {
	boolean authenticate(String email, String password);

	boolean createUser(String username, String email, String password, String phoneNumber, String gender);

	Optional<User> findByEmail(String email);
	
	Optional<User> findByUserId(Integer userId);

	
}
