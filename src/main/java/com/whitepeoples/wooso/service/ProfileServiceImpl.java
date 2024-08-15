package com.whitepeoples.wooso.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.whitepeoples.wooso.dao.ProfileRepository;
import com.whitepeoples.wooso.model.entity.Profile;
import com.whitepeoples.wooso.model.entity.EnumTypes.UserType;

@Service
public class ProfileServiceImpl implements ProfileService{
	
	@Autowired
	private ProfileRepository profileRepository;
	
	
	
	@Override
	public Profile findByEntityIdAndEntityType(Integer entityId, UserType userType) {
		System.out.println("entityId " + entityId + ", entityType " + userType);
		
		Optional<Profile> optionalProfile = profileRepository.findByEntityIdAndEntityType(entityId, userType);
		Profile profile = null ; 
		if(optionalProfile.isPresent()) profile = optionalProfile.get();
			
		return profile;
	}
	
	// 프로필 저장
	@Override
	public void saveProfile(Profile profile) {
		profileRepository.save(profile);
	}

//	@Override
//	public String saveProfileImage(MultipartFile profileImage) {
//        // 이미지 파일을 저장할 경로 설정 (로컬 저장소 혹은 클라우드 스토리지 사용 가능)
////		String uploadDir = "uploaded-images/";
////      String originalFilename = profileImage.getOriginalFilename();
////      String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
////      String storedFilename = UUID.randomUUID().toString() + fileExtension;
//        
////      File dest = new File(uploadDir + storedFilename);
//
//        try {
//            Path uploadPath = Paths.get(uploadDir);
//            if(!Files.exists(uploadPath)) {
//            	Files.createDirectories(uploadPath);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("디렉토리 생성 중 오류가 발생했습니다.", e);
//        }
//        
//        String originalFilename = profileImage.getOriginalFilename();
//        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
//        String storedFilename = UUID.randomUUID().toString() + fileExtension;
//
//        File dest = new File(uploadDir + storedFilename);
//        
//        try {
//            profileImage.transferTo(dest);
//        } catch (IOException e) {
//            throw new RuntimeException("프로필 이미지 저장 중 오류가 발생했습니다.", e);
//        }
//
//        return "/" + uploadDir + storedFilename;
//	}
	
	
}
