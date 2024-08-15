package com.whitepeoples.wooso.service;

import org.springframework.stereotype.Service;

import com.whitepeoples.wooso.model.entity.Profile;

@Service
public interface ProfileService {

	Profile findByEntityIdAndEntityType(Integer entityId, String entityType);

}
