package com.whitepeoples.wooso.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.whitepeoples.wooso.model.entity.Profile;
import com.whitepeoples.wooso.model.entity.EnumTypes.UserType;

@Service
public interface ProfileService {

	Profile findByEntityIdAndEntityType(Integer entityId, UserType userType);
	
	void saveProfile(Profile profile);

//	String saveProfileImage(MultipartFile profileImage);
}
