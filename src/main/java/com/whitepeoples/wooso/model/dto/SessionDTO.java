package com.whitepeoples.wooso.model.dto;

import com.whitepeoples.wooso.model.entity.EnumTypes.UserType;

import lombok.Data;


@Data
public class SessionDTO {

	private UserType userType; 
	private Integer userId;
	private UserProfileDTO userProfileDTO;
	
}
