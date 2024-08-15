package com.whitepeoples.wooso.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.whitepeoples.wooso.model.dto.SessionDTO;
import com.whitepeoples.wooso.model.dto.UserProfileDTO;
import com.whitepeoples.wooso.model.entity.Profile;
import com.whitepeoples.wooso.model.entity.User;
import com.whitepeoples.wooso.model.entity.EnumTypes.UserType;
import com.whitepeoples.wooso.service.ProfileService;
import com.whitepeoples.wooso.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProfileController {
	
    @Value("${app.upload.dir}")
    private String uploadDir;
	
	@Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;
    
    @GetMapping("/profileEdit")
    public String showProfileEditForm(HttpSession session, Model model) {
        // 현재 사용자 정보를 세션에서 가져와서 프로필 편집 폼에 전달
        SessionDTO sessionDTO = (SessionDTO) session.getAttribute("SessionDTO");
        if (sessionDTO != null) {
            model.addAttribute("profile", sessionDTO);
            return "profileEdit";
        } else {
            return "redirect:/login"; // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
        }
    }
    
    
    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute UserProfileDTO userProfileDTO,
    							@RequestParam("profile-image") MultipartFile profileImage,
    							HttpSession session) {
    	
    	// 세션에서 현재 사용자 정보를 가져옴
        SessionDTO sessionDTO = (SessionDTO) session.getAttribute("SessionDTO");

        if(sessionDTO != null) {
        	// 사용자 엔티티를 조회하여 업데이트
        	User user = userService.findByUserId(sessionDTO.getUserId())
        			.orElseThrow(() -> new RuntimeException("사용자 정보를 찾을 수 없습니다."));
        }
        
        Profile profile = profileService.findByEntityIdAndEntityType(sessionDTO.getUserId(), UserType.USER);
        
        if(profile == null) profile = new Profile(); 
        System.out.println("profile set 전임 "+ userProfileDTO);
        profile.setUserDescription(userProfileDTO.getDescription());
        profile.setUserIncome(userProfileDTO.getIncome());
        profile.setUserMbti(userProfileDTO.getMbti());
        profile.setUserAddress(userProfileDTO.getAddress());
        profile.setUserHobby(userProfileDTO.getHobbies());
        profile.setUserAge(userProfileDTO.getAge());
        profile.setEntityId(sessionDTO.getUserId());
        profile.setEntityType(sessionDTO.getUserType());
        // 만약 프로필 이미지를 업로드했다면 파일을 저장하고 URL을 설정하는 로직을 추가
        if (!profileImage.isEmpty()) {
            // 파일 저장 경로 설정
            String originalFilename = profileImage.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String storedFilename = UUID.randomUUID().toString() + fileExtension;
            
            File dest = new File(uploadDir + "/" + storedFilename);

            try {
                profileImage.transferTo(dest);
            } catch (IOException e) {
                throw new RuntimeException("프로필 이미지 저장 중 오류가 발생했습니다.", e);
            }

            // 저장된 이미지의 URL을 설정
            String imageUrl = "/images/" + storedFilename;  // "/images/"는 static 영역의 서브 디렉토리
            System.out.println(imageUrl);
            profile.setUserImgUrl(imageUrl);
            sessionDTO.getUserProfileDTO().setUserImgUrl(imageUrl);
        }

        // 프로필 정보 업데이트
        profileService.saveProfile(profile);
        
        sessionDTO.getUserProfileDTO().setDescription(userProfileDTO.getDescription());
        sessionDTO.getUserProfileDTO().setIncome(userProfileDTO.getIncome());
        sessionDTO.getUserProfileDTO().setMbti(userProfileDTO.getMbti());
        sessionDTO.getUserProfileDTO().setHobbies(userProfileDTO.getHobbies());
        sessionDTO.getUserProfileDTO().setAge(userProfileDTO.getAge());
        sessionDTO.getUserProfileDTO().setAddress(userProfileDTO.getAddress());
        //리펙토링 예정
       // sessionDTO.setUserProfileDTO(userProfileDTO);
//        if (!profileImage.isEmpty()) {
//        	sessionDTO.getUserProfileDTO().setUserImgUrl(profile.getUserImgUrl());
//        }
        
        session.setAttribute("SessionDTO", sessionDTO);
        
        return "redirect:/mainpage";
    }
  
}
