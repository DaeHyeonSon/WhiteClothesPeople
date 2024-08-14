package com.whitepeoples.wooso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.whitepeoples.wooso.service.GuarantorService;
import com.whitepeoples.wooso.service.UserService;

@Controller
public class SignUpController {
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private GuarantorService guarantorService;
	
	@GetMapping("/signup")
    public String signUpForm() {
		System.out.println("signup() 시작");
        return "signup"; // signup.jsp 페이지 반환
    }
	
	@PostMapping("/signup")
	public String signUp(@RequestParam("username") String username,
						 @RequestParam("email") String email,
						 @RequestParam("password") String password,
						 @RequestParam("confirm-password") String confirmPassword,
						 @RequestParam("phone-number") String phoneNumber,
						 @RequestParam("gender") String gender,
						 @RequestParam("userType") String userType,
						 Model model) {
		if(!password.equals(confirmPassword)) {
			model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
			return "signup";
		}
		
		boolean isCreated;
		
		if("user".equals(userType)) {
			System.out.println("userType()");
			isCreated = userService.createUser(username, email, password, phoneNumber, gender);
			
		} else if("guarantor".equals(userType)) {
			isCreated = guarantorService.createGuarantor(username, email, password, phoneNumber, gender);
		} else {
			model.addAttribute("error", "사용자 유형을 선택해주세요.");
			return "signup";
		}
		
		if (isCreated) {
			return "redirect:/login";
		} else {
			model.addAttribute("error","회원가입 중 오류가 발생했습니다.");
			return "signup";
		}
	}
}
