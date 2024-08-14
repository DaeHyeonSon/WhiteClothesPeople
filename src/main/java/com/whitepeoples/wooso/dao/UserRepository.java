package com.whitepeoples.wooso.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whitepeoples.wooso.model.entity.User;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

    // 범위 조회
    Page<User> findAll(Pageable pageable);

    // 단일 조회
    Optional<User> findByUserId(Long id);
    
    Optional<User> findByEmail(String email);

    // 필드를 통한 조회
    Optional<User> findByUsername(String username);

    // 필드를 통한 삭제
    void deleteByUsername(String username);

    // 필드를 통한 업데이트는 일반적으로 엔티티를 받아 처리
    // 저장할 때 자동으로 업데이트

}
