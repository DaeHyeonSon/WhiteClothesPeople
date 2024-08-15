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
	public String loginForm() {
		System.out.println("login() 시작");
		return "login";
	}

	@GetMapping("/mainpage")
	public String mainpage(HttpSession session, Model model) throws Exception {
		System.out.println("mainpage() 시작");
		SessionDTO sessionDTO = (SessionDTO) session.getAttribute("SessionDTO");

		System.out.println("@GetMapping(\"/mainpage\") sessionDTO : " + sessionDTO);
		if (sessionDTO == null) {
	        throw new Exception("세션이 만료되었거나 존재하지 않습니다.");
	    }
		
		Optional<User> optionalUser = userService.findByUserId(sessionDTO.getUserId());
		if (!optionalUser.isPresent()) throw new Exception();

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
			model.addAttribute("hobbies", userProfileDTO.getHobbies());
			model.addAttribute("age", userProfileDTO.getAge());
			model.addAttribute("userImgUrl", userProfileDTO.getUserImgUrl());
			System.out.println("main page : "+ userProfileDTO);
			
		} catch (NullPointerException e) {
			model.addAttribute("username", userProfileDTO.getUsername());
			model.addAttribute("email", userProfileDTO.getEmail());
			model.addAttribute("description", "No description available");
			model.addAttribute("mbti", "N/A");
			model.addAttribute("hobbies", "N/A");
			model.addAttribute("age", "N/A");
			model.addAttribute("userImgUrl", "N/A");
			System.out.println("main page : "+ userProfileDTO);
		}

		return "mainpage";
	}
	
	@GetMapping("/mainpageGuarantor")
	public String mainpageGuarantor(HttpSession session, Model model) throws Exception {
		System.out.println("mainpageGuarantor() 시작");
		
	    SessionDTO sessionDTO = (SessionDTO) session.getAttribute("SessionDTO");
	    System.out.println("@GetMapping(\"/mainpageGuarantor\") sessionDTO : " + sessionDTO);
	    if (sessionDTO == null) {
	        throw new Exception("세션이 만료되었거나 존재하지 않습니다.");
	    }
	    
	    Optional<Guarantor> optionalGuarantor = guarantorService.findByGuarantorId(sessionDTO.getUserId());
		if (!optionalGuarantor.isPresent()) throw new Exception();

	    UserProfileDTO userProfileDTO = sessionDTO.getUserProfileDTO();
	    try {
	        model.addAttribute("guarantorName", userProfileDTO.getUsername());
	        model.addAttribute("guarantorEmail", userProfileDTO.getEmail());
	        model.addAttribute("guarantorDescription", userProfileDTO.getDescription());
	        model.addAttribute("guarantorHashtags", userProfileDTO.getMbti() + " " + userProfileDTO.getHobbies());
	        model.addAttribute("guarantorLocation", "서울, 대한민국");
	    } catch (NullPointerException e) {
	        model.addAttribute("guarantorName", userProfileDTO.getUsername());
	        model.addAttribute("guarantorEmail", userProfileDTO.getEmail());
	        model.addAttribute("guarantorDescription", "No description available");
	        model.addAttribute("guarantorHashtags", "N/A");
	        model.addAttribute("guarantorLocation", "N/A");
	    }
	    
	    return "mainpageGuarantor";
	    
	}

	@PostMapping("/login")
	public String login(UserLoginDTO userLoginDTO, HttpServletRequest request, HttpSession httpSession, Model model)
			throws Exception {

		if (userLoginDTO.getUserType() == UserType.USER) {

			Optional<User> optionalUser = userService.findByEmail(userLoginDTO.getEmail());
			if (!optionalUser.isPresent()) {
				model.addAttribute("error", "잘못된 이메일이 입력되었습니다."); // 이메일이 잘못된 경우
	            return "login";
			}

			boolean isAuthenticated = userService.authenticate(userLoginDTO.getEmail(), userLoginDTO.getPassword());
			if (!isAuthenticated) {
				model.addAttribute("error", "잘못된 비밀번호가 입력되었습니다"); // 비밀번호가 잘못된 경우
	            return "login";
			}
			
			if (isAuthenticated) {
				User user = optionalUser.get();
				SessionDTO sessionDTO = new SessionDTO();
				UserProfileDTO userProfileDTO = new UserProfileDTO();

				Profile profile = profileservice.findByEntityIdAndEntityType(user.getUserId(), UserType.USER);

				// DTO에 데이터 저장

				userProfileDTO.setUsername(user.getUsername());
				userProfileDTO.setEmail(user.getEmail());
				userProfileDTO.setDescription(profile != null ? profile.getUserDescription() : "No description available");
				userProfileDTO.setMbti(profile != null ? profile.getUserMbti() : "N/A");
				userProfileDTO.setHobbies(profile != null ? profile.getUserHobby() : "N/A");
				userProfileDTO.setAge(profile != null ? profile.getUserAge() : null);
				userProfileDTO.setUserImgUrl(profile != null ? profile.getUserImgUrl() : null);
				userProfileDTO.setAddress(profile != null ? profile.getUserAddress() : null);
				sessionDTO.setUserId(user.getUserId());
				sessionDTO.setUserProfileDTO(userProfileDTO);
				sessionDTO.setUserType(UserType.USER);

				httpSession.invalidate(); // 기존 세션 무효화
				httpSession = request.getSession(true); // 새로운 세션 생성
				httpSession.setAttribute("SessionDTO", sessionDTO); // 세션에 저장

				return "redirect:mainpage";
			}
			
			System.out.println("User 로그인 성공");
		}
		
		if (userLoginDTO.getUserType() == UserType.GUARANTOR) {
			
			Optional<Guarantor> optionalGuarantor = guarantorService.findByEmail(userLoginDTO.getEmail());
			if (!optionalGuarantor.isPresent()) {
				model.addAttribute("error", "잘못된 이메일이 입력되었습니다."); // 이메일이 잘못된 경우
	            return "login";
			}			
			boolean isAuthenticated = guarantorService.authenticate(userLoginDTO.getEmail(), userLoginDTO.getPassword());
			if (!isAuthenticated) {
				model.addAttribute("error", "잘못된 비밀번호가 입력되었습니다"); // 비밀번호가 잘못된 경우
	            return "login";
			}

			if (isAuthenticated) {
	            Guarantor guarantor = optionalGuarantor.get();
	            SessionDTO sessionDTO = new SessionDTO();
				UserProfileDTO userProfileDTO = new UserProfileDTO();
	            
				Profile profile = profileservice.findByEntityIdAndEntityType(guarantor.getGuarantorId(), UserType.GUARANTOR);
				
				// DTO에 저장을 위한 로직
				userProfileDTO.setUsername(guarantor.getName());
				userProfileDTO.setEmail(guarantor.getEmail());
				userProfileDTO.setDescription(profile != null ? profile.getUserDescription() : "No description available");
	            userProfileDTO.setMbti(profile != null ? profile.getUserMbti() : "N/A");
	            userProfileDTO.setHobbies(profile != null ? profile.getUserHobby() : "N/A");
	            userProfileDTO.setAge(profile != null ? profile.getUserAge() : null);

	            sessionDTO.setUserId(guarantor.getGuarantorId());
	            sessionDTO.setUserProfileDTO(userProfileDTO);
	            sessionDTO.setUserType(UserType.GUARANTOR);
				
	            httpSession.invalidate(); // 기존 세션 무효화
	            httpSession = request.getSession(true); // 새로운 세션 생성
	            httpSession.setAttribute("SessionDTO", sessionDTO); // 세션에 저장
	            
				return "redirect:/mainpageGuarantor";
			}
			System.out.println("Guarantor 로그인 완료");
		}

		model.addAttribute("error", "Invalid username or password");
		return "login"; // 로그인 실패 시 다시 로그인 페이지로
	}
	
}
