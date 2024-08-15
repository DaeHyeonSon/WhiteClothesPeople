package com.whitepeoples.wooso.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.whitepeoples.wooso.model.entity.Guarantor;
import com.whitepeoples.wooso.model.entity.Matching;
import com.whitepeoples.wooso.model.entity.User;

public interface MatchingRepository extends JpaRepository<Matching, Integer> {


    
    // 
    Page<Matching> findByMatchTypeAndMatchStatus(String matchType, String matchStatus, Pageable pageable);

    // 필드를 통한 삭제
   // void deleteByMatchType(String matchType);
    
    
    // 범위 조회
    Page<Matching> findAll(Pageable pageable);

    // 단일 조회
    Optional<Matching> findByMatchId(Integer id);

    // 필드를 통한 조회
    Optional<Matching> findByMatchType(String matchType);

    // 필드를 통한 삭제
    void deleteByMatchType(String matchType);
    
    // 단일 조회: requestUser와 requestMatchmaker를 기준으로 조회
    Optional<Matching> findByRequestUserAndRequestMatchmaker(User requestUser, Guarantor requestMatchmaker);

    // 단일 조회: responseUser와 responseMatchmaker를 기준으로 조회
    Optional<Matching> findByResponseUserAndResponseMatchmaker(User responseUser, Guarantor responseMatchmaker);

}