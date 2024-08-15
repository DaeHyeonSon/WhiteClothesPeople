package com.whitepeoples.wooso.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateRequestMatchingDTO {
	@NonNull
	private Integer userId; 
	@NonNull
	private Integer guarantorId;
	
	private String description; 
	
	private String mendatory;
	
	
}
