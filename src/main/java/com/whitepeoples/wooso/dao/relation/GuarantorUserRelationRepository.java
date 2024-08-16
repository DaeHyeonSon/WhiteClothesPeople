package com.whitepeoples.wooso.dao.relation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.whitepeoples.wooso.model.entity.relations.GuarantorUserRelation;


@Repository
public interface GuarantorUserRelationRepository extends JpaRepository<GuarantorUserRelation, Integer> {
    // Status로 조회
    List<GuarantorUserRelation> findByStatus(String status);

    // Guarantor ID로 조회
   // List<GuarantorUserRelation> findByGuarantor_Id(Integer guarantorId);

    // follwer ID 로 조회
//    List<GuarantorUserRelation> findByUser_Id(Integer userId);

    // Responder ID와 Responder Type으로 조회
    List<GuarantorUserRelation> findByFollowerIdAndFollowerType(Integer followeId, String followerType);

    // 페이지로 조회
    Page<GuarantorUserRelation> findAll(Pageable pageable);
    
    // 단일 조회
//    Optional<GuarantorUserRelation> findById(Integer relationId);
    
    
    @Query("SELECT r FROM GuarantorUserRelation r " +
    	       "WHERE (r.followerGuarantor.guarantorId = :guarantorId AND " +
    	       "r.followingUser.userId = :userId AND " +
    	       "r.status = 'following' AND " +
    	       "r.followerId = :userId AND r.followerType = 'user') " +
    	       "OR (r.followerGuarantor.guarantorId = :guarantorId AND " +
    	       "r.followingUser.userId = :userId AND " +
    	       "r.status = 'following' AND " +
    	       "r.followerId = :guarantorId AND r.followerType = 'guarantor')")
    	List<GuarantorUserRelation> findGuarantorUserMutualFollowing(
    	       @Param("guarantorId") Integer guarantorId, 
    	       @Param("userId") Integer userId);

    	@Query("SELECT COUNT(r) FROM GuarantorUserRelation r " +
    	       "WHERE (r.followerGuarantor.guarantorId = :guarantorId AND " +
    	       "r.followingUser.userId = :userId AND " +
    	       "r.status = 'following' AND " +
    	       "r.followerId = :userId AND r.followerType = 'user') " +
    	       "OR (r.followerGuarantor.guarantorId = :guarantorId AND " +
    	       "r.followingUser.userId = :userId AND " +
    	       "r.status = 'following' AND " +
    	       "r.followerId = :guarantorId AND r.followerType = 'guarantor')")
    	Long countGuarantorUserMutualFollowing(
    	       @Param("guarantorId") Integer guarantorId, 
    	       @Param("userId") Integer userId);
}