package com.whitepeoples.wooso.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.whitepeoples.wooso.model.dto.SessionDTO;
import com.whitepeoples.wooso.model.dto.UserProfileDTO;
import com.whitepeoples.wooso.model.dto.UserProfileUpdateDTO;
import com.whitepeoples.wooso.model.entity.Profile;
import com.whitepeoples.wooso.model.entity.EnumTypes.UserType;

import com.whitepeoples.wooso.model.entity.Profile;

import com.whitepeoples.wooso.model.entity.Profile;
import com.whitepeoples.wooso.model.entity.User;

@Service
public interface ProfileService {

	Optional<Profile> findByEntityIdAndEntityType(Integer entityId, UserType userType);
	
	UserProfileDTO saveProfile(SessionDTO sessionDTO ,  UserProfileUpdateDTO userProfileUpdateDTO)  throws Exception;

//	String saveProfileImage(MultipartFile profileImage);

}
