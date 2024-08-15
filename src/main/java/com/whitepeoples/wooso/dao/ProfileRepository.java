package com.whitepeoples.wooso.dao;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.whitepeoples.wooso.model.entity.Profile;
import com.whitepeoples.wooso.model.entity.EnumTypes.UserType;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    // 범위 조회
    Page<Profile> findAll(Pageable pageable);

    // 단일 조회
    Optional<Profile> findByProfileId(Integer id);

    // 필드를 통한 조회 

	Optional<Profile> findByEntityIdAndEntityType(Integer entityId, UserType userType);

//	Profile findByEntityIdAndEntityType(Integer entityId, String entityType);

    // 필드를 통한 삭제
   // void deleteByUserAge(Integer userAge);

}
