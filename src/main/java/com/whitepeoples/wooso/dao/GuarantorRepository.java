package com.whitepeoples.wooso.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.whitepeoples.wooso.model.entity.Guarantor;


public interface GuarantorRepository extends JpaRepository<Guarantor, Integer> {

    // 범위 조회
    Page<Guarantor> findAll(Pageable pageable);

    // 단일 조회
    Optional<Guarantor> findByGuarantorId(Integer id);
    
    Optional<Guarantor> findByEmail(String email);

    // 필드를 통한 조회
    Optional<Guarantor> findByName(String name);

    // 필드를 통한 삭제
    void deleteByName(String name);
}
