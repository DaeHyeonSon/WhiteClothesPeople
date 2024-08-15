package com.whitepeoples.wooso.service;

import org.springframework.stereotype.Service;

import com.whitepeoples.wooso.model.dto.request.CreateRequestMatchingDTO;
import com.whitepeoples.wooso.model.dto.request.UpdateRequestMatchingDTO;


@Service
public interface MatchingService {
	
	 public Boolean createMatchingRequest(CreateRequestMatchingDTO createMatchingRequestDTO) throws Exception;
	 
	 public Boolean updateMatchingRequest(UpdateRequestMatchingDTO updateRequestMatchingDTO);
	 
	 public Boolean deleteMatchingRequest(); 
	 
	 
	 public Boolean createWoosoMatching();
	 
	 public Boolean updateWoosoMatching();
	 
	 public Boolean deleteWoosoMatching();


}
