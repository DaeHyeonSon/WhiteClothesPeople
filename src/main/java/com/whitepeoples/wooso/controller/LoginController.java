package com.whitepeoples.wooso.controller;

import java.net.http.HttpRequest;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.whitepeoples.wooso.model.dto.SessionDTO;
import com.whitepeoples.wooso.model.dto.UserLoginDTO;
import com.whitepeoples.wooso.model.dto.UserProfileDTO;
import com.whitepeoples.wooso.model.entity.Guarantor;
import com.whitepeoples.wooso.model.entity.Profile;
import com.whitepeoples.wooso.model.entity.User;
import com.whitepeoples.wooso.model.entity.EnumTypes.UserType;
import com.whitepeoples.wooso.service.GuarantorService;
import com.whitepeoples.wooso.service.ProfileService;
import com.whitepeoples.wooso.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import net.bytebuddy.implementation.bytecode.Throw;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GuarantorService guarantorService;
	
	@Autowired
	private ProfileService profileservice;
		
	@GetMapping("/login")
    public String loginForm(){
        System.out.println("login() 시작");
        return "login";
    }
	
	@GetMapping("/mainpage")
	public String mainpage(HttpSession session, Model model) throws Exception {
		System.out.println("mainpage() 시작");
		SessionDTO sessionDTO = (SessionDTO) session.getAttribute("SessionDTO");
		
		System.out.println("@GetMapping(\"/mainpage\") sessionDTO : "+sessionDTO);
		Optional<User> optionalUser = userService.findByUserId(sessionDTO.getUserId());
		if (!optionalUser.isPresent())throw new Exception();

//		System.out.println("User 로그인 완료");
//		if(isAuthenticated) {
//			User user = optionalUser.get();
		
		UserProfileDTO userProfileDTO = sessionDTO.getUserProfileDTO();
		try {
			// Profile profile =
			// profileservice.findByEntityIdAndEntityType(user.getUserId(),"USER");

			model.addAttribute("username", userProfileDTO.getUsername());
			model.addAttribute("email", userProfileDTO.getEmail());
			model.addAttribute("description", userProfileDTO.getDescription());
			model.addAttribute("mbti", userProfileDTO.getMbti());
			model.addAttribute("hobby", userProfileDTO.getHobby());
			model.addAttribute("age", userProfileDTO.getAge());
		} catch (NullPointerException e) {
			model.addAttribute("username", userProfileDTO.getUsername());
			model.addAttribute("email", userProfileDTO.getEmail());
			model.addAttribute("description", "No description available");
			model.addAttribute("mbti", "N/A");
			model.addAttribute("hobby", "N/A");
			model.addAttribute("age", "N/A");
		}

		return "mainpage";
	}
	
	@PostMapping("/login")
	public String login(UserLoginDTO userLoginDTO, 
			 			HttpServletRequest request,
						HttpSession httpSession,
						Model model) throws Exception {
		
		
//		Optional<User> optionalUser = userService.findByEmail(userLoginDTO.getEmail());
//		Optional<Guarantor> optionalGuarantor = guarantorService.findByEmail(userLoginDTO.getEmail());

		if (userLoginDTO.getUserType() == UserType.USER) {
			
			Optional<User> optionalUser = userService.findByEmail(userLoginDTO.getEmail());
			if(!optionalUser.isPresent()) throw new Exception();
			
			boolean isAuthenticated = userService.authenticate(userLoginDTO.getEmail(),userLoginDTO.getPassword());
			System.out.println("User 로그인 완료");
			
			if(isAuthenticated) {
				User user = optionalUser.get();
				SessionDTO sessionDTO = new SessionDTO();
				UserProfileDTO userProfileDTO = new UserProfileDTO();
				
				Profile profile = profileservice.findByEntityIdAndEntityType(user.getUserId(),"USER");
					
					
					 // DTO에 데이터 저장
		          
		            userProfileDTO.setUsername(user.getUsername());
		            userProfileDTO.setEmail(user.getEmail());
		            userProfileDTO.setDescription(profile != null ? profile.getUserDescription() : "No description available");
		            userProfileDTO.setMbti(profile != null ? profile.getUserMbti() : "N/A");
		            userProfileDTO.setHobby(profile != null ? profile.getUserHobby() : "N/A");
		            userProfileDTO.setAge(profile != null ? profile.getUserAge() : null);

		            sessionDTO.setUserId(user.getUserId());
		            sessionDTO.setUserProfileDTO(userProfileDTO);
		            sessionDTO.setUserType(UserType.USER);
				
				httpSession.invalidate(); // 기존 세션 무효화
				httpSession = request.getSession(true); // 새로운 세션 생성
				httpSession.setAttribute("SessionDTO", sessionDTO);
				
				return "redirect:mainpage";
			}
        }
		if (userLoginDTO.getUserType() == UserType.GUARANTOR) {
			Optional<Guarantor> optionalGuarantor = guarantorService.findByEmail(userLoginDTO.getEmail());
			if(!optionalGuarantor.isPresent()) throw new Exception("입력하신 정보가 주선자 계정이 아닙니다");
			boolean isAuthenticated = guarantorService.authenticate(userLoginDTO.getEmail(),userLoginDTO.getPassword());
			
			if(isAuthenticated) {
        	return "testGuarantor";
			}
        }
		
		model.addAttribute("error", "Invalid username or password");
        return "login"; // 로그인 실패 시 다시 로그인 페이지로
	}
}
