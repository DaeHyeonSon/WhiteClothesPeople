package com.whitepeoples.wooso.model.dto;

import com.whitepeoples.wooso.model.entity.EnumTypes.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SessionDTO {

	private UserType userType; 
	private Integer userId;
	private UserProfileDTO userProfileDTO;
	
}
