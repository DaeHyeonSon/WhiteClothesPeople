package com.whitepeoples.wooso.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.whitepeoples.wooso.model.entity.Guarantor;
import com.whitepeoples.wooso.model.entity.RequestMatching;

public interface RequestMatchingRepository extends JpaRepository<RequestMatching, Integer> {

    // requester_id, guarantor_id, status로 단일 조회
    Optional<RequestMatching> findByRequester_UserIdAndGuarantor_GuarantorIdAndStatus(Integer requesterId, Integer guarantorId, String status);

    // requester_id를 사용한 페이지 조회
    Page<RequestMatching> findByRequester_UserId(Integer requesterId, Pageable pageable);

    // status를 사용한 페이지 조회
    Page<RequestMatching> findByStatus(String status, Pageable pageable);
    
    List<RequestMatching> findByGuarantor(Guarantor guarantor);
}