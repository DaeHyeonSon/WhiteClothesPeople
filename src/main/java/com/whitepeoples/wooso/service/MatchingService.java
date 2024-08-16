package com.whitepeoples.wooso.service;

import org.springframework.stereotype.Service;

import com.whitepeoples.wooso.model.dto.SessionDTO;
import com.whitepeoples.wooso.model.dto.request.CreateRequestMatchingDTO;
import com.whitepeoples.wooso.model.dto.request.UpdateRequestMatchingDTO;

import jakarta.servlet.http.HttpSession;


@Service
public interface MatchingService {
	
	 public Boolean isUserMastchingAvailable(SessionDTO sessionDTO) throws Exception;
	
	 public Boolean createRequestMatching(CreateRequestMatchingDTO createMatchingRequestDTO) throws Exception;
	 
	 public Boolean updateRequestMatching(SessionDTO sessionDTO , UpdateRequestMatchingDTO updateRequestMatchingDTO)throws Exception;
//	 
//	 public Boolean deleteRequestMatching(); 
//	 
//	 
//	 private void updatePartner() throws Exception {
//	}
//	 
//	 private void cancelRequest() throws Exception {
//	}
//	 
//	 
//	 public Boolean createWoosoMatching();
//	 
//	 public Boolean updateWoosoMatching();
//	 
//	 public Boolean deleteWoosoMatching();


}
