package com.whitepeoples.wooso.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.whitepeoples.wooso.model.entity.Guarantor;
import com.whitepeoples.wooso.model.entity.User;
import com.whitepeoples.wooso.service.GuarantorService;
import com.whitepeoples.wooso.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GuarantorService guarantorService;
		
	@GetMapping("/login")
    public String loginForm(){
        System.out.println("login() 시작");
        return "login";
    }
	
	@PostMapping("/login")
	public String login(@RequestParam("email") String email,
						@RequestParam("password") String password,
						Model model) {
		Optional<User> optionalUser = userService.findByEmail(email);
		Optional<Guarantor> optionalGuarantor = guarantorService.findByEmail(email);
		
		if (optionalUser.isPresent()) {
			boolean isAuthenticated = userService.authenticate(email,password);
			
			if(isAuthenticated) {
        	return "testUser";
			}
        }
		
		if (optionalGuarantor.isPresent()) {
			boolean isAuthenticated = guarantorService.authenticate(email,password);
			
			if(isAuthenticated) {
        	return "testGuarantor";
			}
        }
		
		model.addAttribute("error", "Invalid username or password");
        return "login"; // 로그인 실패 시 다시 로그인 페이지로
	}
}
