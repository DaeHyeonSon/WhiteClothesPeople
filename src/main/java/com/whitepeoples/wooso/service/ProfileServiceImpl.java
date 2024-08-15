package com.whitepeoples.wooso.service;

import java.util.Optional;

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

	
	
}
