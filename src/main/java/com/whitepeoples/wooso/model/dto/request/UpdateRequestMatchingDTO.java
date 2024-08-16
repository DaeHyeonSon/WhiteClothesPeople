package com.whitepeoples.wooso.model.dto.request;

import com.whitepeoples.wooso.model.entity.Guarantor;
import com.whitepeoples.wooso.model.entity.EnumTypes.RequestMatchingUpdateType;
import com.whitepeoples.wooso.model.entity.EnumTypes.UserType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateRequestMatchingDTO {

	@NonNull
	private Integer requestId;
	@NonNull
	private Integer userId;
	@NonNull
	private UserType userType;
	@NonNull
	private Guarantor originalGuarantor;
	
	private Guarantor newGuarantor;
	@NonNull
	private RequestMatchingUpdateType jobType; 
}
