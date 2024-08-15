package com.whitepeoples.wooso.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.whitepeoples.wooso.dao.UserRepository;
import com.whitepeoples.wooso.model.entity.User;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	
	@Override
	public boolean authenticate(String email, String password) {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		
		if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return true;
            }
        }
		
		return false;
	}
	
	@Override
	public boolean createUser(String username, String email, String password, String phoneNumber, String gender) {
		if(userRepository.findByUsername(username).isPresent()){
			return false;
		}
		
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setEmail(email);
		newUser.setPassword(passwordEncoder.encode(password));
		newUser.setPhoneNumber(phoneNumber);
		newUser.setGender(gender);
		newUser.setVerificationStatus(false);
		
		userRepository.save(newUser);
		return true;
	}


	@Override
	public Optional<User> findByUserId(Integer userId) {
		return userRepository.findById(userId);
	}

}

















