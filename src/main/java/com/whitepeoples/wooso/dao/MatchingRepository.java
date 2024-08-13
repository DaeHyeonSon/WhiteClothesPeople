package com.whitepeoples.wooso.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.whitepeoples.wooso.model.entity.Matching;

public interface MatchingRepository extends JpaRepository<Matching, Integer> {

    // 범위 조회
    Page<Matching> findAll(Pageable pageable);

    // 단일 조회
    Optional<Matching> findByMatchId(Integer id);

    // 필드를 통한 조회
    Optional<Matching> findByMatchType(String matchType);

    // 필드를 통한 삭제
   // void deleteByMatchType(String matchType);

}