package com.whitepeoples.wooso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whitepeoples.wooso.dao.ProfileRepository;
import com.whitepeoples.wooso.model.entity.Profile;

@Service
public class ProfileServiceImpl implements ProfileService{
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Override
	public Profile findByEntityIdAndEntityType(Integer entityId, String entityType) {
		return profileRepository.findByEntityIdAndEntityType(entityId, entityType);
	}
}
