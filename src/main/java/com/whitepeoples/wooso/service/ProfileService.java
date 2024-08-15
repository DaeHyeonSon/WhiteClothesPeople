package com.whitepeoples.wooso.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.whitepeoples.wooso.model.entity.Profile;

@Service
public interface ProfileService {

	Optional<Profile> findByEntityIdAndEntityType(Integer entityId, String entityType);

}
