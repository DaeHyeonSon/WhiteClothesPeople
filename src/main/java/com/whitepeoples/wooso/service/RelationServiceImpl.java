package com.whitepeoples.wooso.service;

import org.springframework.stereotype.Service;

import com.whitepeoples.wooso.dao.GuarantorRepository;
import com.whitepeoples.wooso.dao.UserRepository;
import com.whitepeoples.wooso.dao.relation.GuarantorGuarantorRelationRepository;
import com.whitepeoples.wooso.dao.relation.GuarantorUserRelationRepository;
import com.whitepeoples.wooso.dao.relation.UserUserRelationRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RelationServiceImpl implements RelationService {

	private final UserRepository userRepository;
	private final GuarantorRepository guarantorRepository;
	private final GuarantorGuarantorRelationRepository guarantorGuarantorRelationRepository;
	private final GuarantorUserRelationRepository guarantorUserRelationRepository;
	private final UserUserRelationRepository userUserRelationRepository;
	


	@Override
	@Transactional
	public Boolean createFollow() {
		// TODO Auto-generated method stub
		//  주선자 , 주선자 간 팔로우인가? 
		
		// 주선자, 소개자 간 팔로우인가? 
		
		// 소개자, 소개자 간 팔로우인가? 일단 이작업은 보류 
		

		return false;
	}



	@Override
	public Boolean deleteFollow() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @Override
	 * 
	 * @Transactional public Boolean updateFollow() { // TODO Auto-generated method
	 * stub
	 * 
	 * // 주선자 혹은 소개자 정보를 받아서 있는지 존재하는지 확인
	 * 
	 * // return false; }
	 */


}
