package com.whitepeoples.wooso.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.whitepeoples.wooso.dao.GuarantorRepository;
import com.whitepeoples.wooso.model.entity.Guarantor;


@Service
public class GuarantorServiceImpl implements GuarantorService{
	@Autowired
	private GuarantorRepository guarantorRepository;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Optional<Guarantor> findByEmail(String email) {
		return guarantorRepository.findByEmail(email);
	}

	@Override
	public boolean authenticate(String email, String password) {
		Optional<Guarantor> optionalGuarantor = guarantorRepository.findByEmail(email);
		
		if(optionalGuarantor.isPresent()) {
			Guarantor guarantor = optionalGuarantor.get();
			if(passwordEncoder.matches(password, guarantor.getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean createGuarantor(String username, String email, String password, String phoneNumber, String gender) {
		Optional<Guarantor> existingGuarantor = guarantorRepository.findByEmail(email);
		if(existingGuarantor.isPresent()) {
			return false; // 이미 존재하는 주선자
		}
		
		Guarantor newGuarantor = new Guarantor();
		newGuarantor.setName(username);
		newGuarantor.setEmail(email);
		newGuarantor.setPassword(passwordEncoder.encode(password));
		newGuarantor.setPhoneNumber(phoneNumber);
		newGuarantor.setGender(gender);
		newGuarantor.setVerificationStatus(false);
		
		guarantorRepository.save(newGuarantor);
		return true;
	}

	@Override
	public Optional<Guarantor> findByGuarantorId(Integer userId) {
		return guarantorRepository.findById(userId);
	}

}
