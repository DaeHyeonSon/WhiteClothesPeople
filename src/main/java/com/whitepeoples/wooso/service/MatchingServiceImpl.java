package com.whitepeoples.wooso.service;


import java.util.Optional;

import org.springframework.stereotype.Service;

import com.whitepeoples.wooso.dao.GuarantorRepository;
import com.whitepeoples.wooso.dao.MatchingRepository;
import com.whitepeoples.wooso.dao.ProfileRepository;
import com.whitepeoples.wooso.dao.RequestMatchingRepository;
import com.whitepeoples.wooso.dao.SubscriptionRepository;
import com.whitepeoples.wooso.dao.UserRepository;
import com.whitepeoples.wooso.dao.relation.GuarantorUserRelationRepository;
import com.whitepeoples.wooso.model.dto.SessionDTO;
import com.whitepeoples.wooso.model.dto.request.CreateRequestMatchingDTO;
import com.whitepeoples.wooso.model.dto.request.UpdateRequestMatchingDTO;
import com.whitepeoples.wooso.model.entity.Guarantor;
import com.whitepeoples.wooso.model.entity.Matching;
import com.whitepeoples.wooso.model.entity.RequestMatching;
import com.whitepeoples.wooso.model.entity.Subscription;
import com.whitepeoples.wooso.model.entity.User;
import com.whitepeoples.wooso.model.entity.EnumTypes.MatchingRequestType;
import com.whitepeoples.wooso.model.entity.EnumTypes.RequestMatchingUpdateType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;




@RequiredArgsConstructor
@Service
public class MatchingServiceImpl implements MatchingService {
	private final MatchingRepository matchingRepository;
	private final RequestMatchingRepository requestMatchingRepository;
	private final GuarantorUserRelationRepository guarantorUserRelationRepository;
 	private final ProfileRepository profileRepository;
 	private final UserRepository userRepository;
 	private final GuarantorRepository guarantorRepository;
 	private final SubscriptionRepository subscriptionRepository;

 	
 	

	@Override
	public Boolean createRequestMatching(CreateRequestMatchingDTO createMatchingRequestDTO) throws Exception {
		Boolean res = false;
		
		
		// 유효한 유저인가 확인
		Optional<User> oUser = userRepository.findById(createMatchingRequestDTO.getUserId());
		Optional<Guarantor> oGuarantor = guarantorRepository.findById(createMatchingRequestDTO.getGuarantorId());
		// 주선자 또는  유저가 존재하지 않는다면! 에러 throw
		if(!oUser.isPresent() || !oGuarantor.isPresent()) throw new Exception();
		
		User user = oUser.get();
		Guarantor guarantor = oGuarantor.get();
		// 사용자와 주선자  프로파일이 유효한지 체크
		if(user.getUserProfile() == null ) throw new Exception();
		
		Optional<Subscription> oSubscription =  subscriptionRepository.findFirstByUser_UserId(user.getUserId());
		if(!oSubscription.isPresent()) throw new Exception();
	
		
		// 사용자와 active상태의 MatchingRequest가 있는가? 
		Optional<RequestMatching> oRequestMatching = 
				requestMatchingRepository.
				findByRequester_UserIdAndGuarantor_GuarantorIdAndStatus(user.getUserId(),
						createMatchingRequestDTO.getGuarantorId() 
						, "active");
				
		if(oRequestMatching.isPresent())  throw new Exception();
		
		// 사용자의 구독 모델 && 매칭개수 확인 
		Subscription subscription = oSubscription.get();
		if(!(subscription.getRemainingMatches() > 0)) throw new Exception();
		
		// 사용자의 매칭 개수 제해야함 
		subscription.setRemainingMatches(subscription.getRemainingMatches() - 1);	
		
		// MatchingRequest 생성  
		requestMatchingRepository.save(
				RequestMatching.builder()
				.requester(user)
				.guarantor(guarantor)
				.description(createMatchingRequestDTO.getDescription())
				.mandatory(createMatchingRequestDTO.getMendatory())
				.build()
				);
		res = true;
		
		// 주선자한태 알림
		//FCM 알림을 통해 알림 
		
		return res;
	}

	@Override
	public Boolean updateRequestMatching(SessionDTO sessionDTO ,  UpdateRequestMatchingDTO updateRequestMatchingDTO) throws Exception {
		
		// TODO Auto-generated method stub
		Boolean res = false;
		
		// MatchingRequest를 변경할 수 있는 상태인가? 어떻게 확인? -> MatchingRequest 상태로 확인
		
		Optional<RequestMatching> oRequestMatching = requestMatchingRepository.findById(updateRequestMatchingDTO.getRequestId());
		
		if(!oRequestMatching.isPresent()) throw new Exception();
		RequestMatching requestMatching = oRequestMatching.get();
		if(requestMatching.getStatus() == MatchingRequestType.APPROVED ||
				requestMatching.getStatus() == MatchingRequestType.CANCELlED ||
				requestMatching.getStatus() == MatchingRequestType.REJECTED)throw new Exception("해당 요청은 변경할 수 없는 상태입니다.");
		
		
		//updateRequestMatchingDTO

//		Optional<Matching> matching = matchingRepository.findById(requestMatching.getRequestId());
//		if(!matching.isPresent()) throw new Exception("존재하지 않는 ");
		// MatchingRequest로 생성된 Matching이 있으면 fail 

		//  무엇을 업데이트 할것인가?? 다른 주선자 로 업데이트? 
		
		
		// 무엇을 반환할 것인가?? 

		return res;
	}

//	@Override
//	public Boolean deleteRequestMatching() {
//		// TODO Auto-generated method stub
//		
//		//MatchingRequest를 취소할 수 있는 상태인가? 기록삭제?
//		
//		
//		// MatchingRequest 상태
//		return null;
//	}
//
//	
//	// 매칭 생성이 곧 -> 소개자 - 소개자간 매칭 요청이라고 보면됨 
//	
//	// 매칭 엔티티가 생성될 때 invite로 생성됨 
//	// 누가 invite했는지 어떻게 판단하는가? -> 
//	@Override
//	public Boolean createWoosoaMtching() {
//		// TODO Auto-generated method stub
//		
//		
//		// MatchingRequest의 DTO가 오게됨 
//		
//		// 유효한 Request Matching 인가? 
//		
//		
//		// 소개자의 구독 모델 && 매칭개수 확인 
//		
//		// MatchingRequest 상태 completed 으로 업데이트 
//		
//		// 
//		
//		return null;
//	}
//
//	@Override
//	public Boolean updateWoosoMatching() {
//		// TODO Auto-generated method stub
//		
//		// 매칭을 변경할 수 있는 상태인가? 
//		
//		// 매칭 상태 변경 만일단 . 
//		
//		return null;
//	}
//
//	
//	@Override
//	public Boolean deleteWoosoMatching() {
//		// TODO Auto-generated method stub
//		
//		// 매칭 삭제를 못함 정산과 관련되어 있기 때문 
//		
//		// 사용자에게 보이지 않도록 처리 
//
//		return null;
//	}
//
	@Override
	public Boolean isUserMastchingAvailable(SessionDTO sessionDTO) throws Exception {
		// TODO Auto-generated method stub
		Boolean res = true;
//		Optional<User> oUser = userRepository.findById(sessionDTO.getUserId());
//		if(oUser.isEmpty()) throw new Exception("현재 존재하지 않는 유저입니다.");
//		User user = oUser.get();
//		if(!user.getVerificationStatus() || user.getUserProfile() == null) throw new Exception("Matching 전에 Profile을 작성해주세요!");
//		Subscription subscription = subscriptionRepository.findByUser(oUser.get());
//		if(subscription == null) throw new Exception("현재 구매하신 구독권이 없는 상태입니다.");
//		if(subscription.getRemainingMatches() <= 0) throw new Exception("사용 가능한 매칭 토큰이 부족해요!");
		Optional<User> oUser = userRepository.findById(sessionDTO.getUserId());
		if(oUser.isEmpty()) return  false;
		User user = oUser.get();
		if(!user.getVerificationStatus() || user.getUserProfile() == null)return false;
		Subscription subscription = subscriptionRepository.findByUser(oUser.get());
		if(subscription == null) return  false;
		if(subscription.getRemainingMatches() <= 0) return false;
		
		
		return res;
	}
//
//	@Override
//	public Boolean createWoosoMatching() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
