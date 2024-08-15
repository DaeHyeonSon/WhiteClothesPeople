package com.whitepeoples.wooso.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.whitepeoples.wooso.model.entity.Guarantor;

@Service
public interface GuarantorService {
	Optional<Guarantor> findByEmail(String email);
	boolean authenticate(String email, String password);
	boolean createGuarantor(String username, String email, String password, String phoneNumber, String gender);
	Optional<Guarantor> findByGuarantorId(Integer userId);
	
}
