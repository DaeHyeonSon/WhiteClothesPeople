package com.whitepeoples.wooso.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.whitepeoples.wooso.dao.ProfileRepository;
import com.whitepeoples.wooso.dao.UserRepository;
import com.whitepeoples.wooso.model.dto.ReviewDTO;
import com.whitepeoples.wooso.model.dto.SessionDTO;
import com.whitepeoples.wooso.model.dto.UserProfileDTO;
import com.whitepeoples.wooso.model.dto.UserProfileUpdateDTO;
import com.whitepeoples.wooso.model.entity.Profile;
import com.whitepeoples.wooso.model.entity.Review;
import com.whitepeoples.wooso.model.entity.User;
import com.whitepeoples.wooso.model.entity.EnumTypes.UserType;

import jakarta.transaction.Transactional;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private FileService fileService;

	@Autowired
	private UserService userService;

	// ModelMapper 인스턴스 생성 (이는 보통 Spring Bean으로 등록하여 사용합니다)
	@Autowired
	private UserRepository userRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public ProfileServiceImpl() {
		// Configure ModelMapper mappings
		modelMapper.addMappings(new PropertyMap<Profile, UserProfileDTO>() {
			@Override
			protected void configure() {
				map().setDescription(source.getUserDescription());
				map().setHobbies(source.getUserHobby());
				map().setAge(source.getUserAge());
			}
		});
	}

	@Override
	public Optional<Profile> findByEntityIdAndEntityType(Integer entityId, UserType userType) {
		System.out.println("entityId " + entityId + ", entityType " + userType);

		Optional<Profile> optionalProfile = profileRepository.findByEntityIdAndEntityType(entityId, userType);

		return optionalProfile;
	}

	// 프로필 저장
	@Override
	@Transactional
	public UserProfileDTO saveProfile(SessionDTO sessionDTO, UserProfileUpdateDTO userProfileUpdateDTO) throws Exception {
		User user = null;
		if (sessionDTO != null) {
			// 사용자 엔티티를 조회하여 업데이트
			Optional<User> oUser = userService.findByUserId(sessionDTO.getUserId());
			if(!oUser.isPresent())throw new Exception("현재 존재하지 않는 계정입니다.");
			user = oUser.get();
		}

		Optional<Profile> oProfile = findByEntityIdAndEntityType(sessionDTO.getUserId(), sessionDTO.getUserType());
		Profile profile = null;

		if (!oProfile.isPresent())profile = new Profile();
		else profile = oProfile.get();
		
		System.out.println("profile set 전임 " + userProfileUpdateDTO);
		profile.setUserDescription(userProfileUpdateDTO.getDescription());
		profile.setUserIncome(userProfileUpdateDTO.getIncome());
		profile.setUserMbti(userProfileUpdateDTO.getMbti());
		profile.setUserAddress(userProfileUpdateDTO.getAddress());
		profile.setUserHobby(userProfileUpdateDTO.getHobbies());
		profile.setUserAge(userProfileUpdateDTO.getAge());
		profile.setEntityId(sessionDTO.getUserId());
		
//		String type = sessionDTO.getUserType().name();
		if(sessionDTO.getUserType() == UserType.USER)profile.setEntityType(UserType.USER);
		if(sessionDTO.getUserType() == UserType.GUARANTOR)profile.setEntityType(UserType.GUARANTOR);
		
		if(userProfileUpdateDTO.getProfileImage() != null ) {
			Optional<String> userImgUrl = fileService.uploadFile(userProfileUpdateDTO.getProfileImage());
	
			if(userImgUrl.isPresent())profile.setUserImgUrl(userImgUrl.get());
		}
		profile = profileRepository.save(profile);
		
		// 
		UserProfileDTO userProfileDTO = modelMapper.map(profile, UserProfileDTO.class);
		userProfileDTO.setUsername(userProfileUpdateDTO.getUsername());
		user.setVerificationStatus(true);
		user.setUserProfile(profile);
		// 현재 이메일이 업데이트가 안되는 상황임 일단 이렇게라도 세션을 유지.. 
		// 세션은 update 발생시마다 DB의 값과 동기화를 해주야함
		
		// 세션동기화용 함수를 만드는 것도 낫베드
		userProfileDTO.setEmail(sessionDTO.getUserProfileDTO().getEmail());
		System.out.println("userProfileDTO : "+userProfileDTO);
		return userProfileDTO;
	}

}
