package com.whitepeoples.wooso.model.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class UpdateRequestMatchingDTO {

	@NonNull
	private Integer requestId;
	@NonNull
	private Integer userId;
	@NonNull
	private String userType;
	@NonNull
	private String updateType;
}
