package com.whitepeoples.wooso.dao.relation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.whitepeoples.wooso.model.entity.relations.GuarantorGuarantorRelation;

@Repository
public interface GuarantorGuarantorRelationRepository extends JpaRepository<GuarantorGuarantorRelation, Integer> {

    // Mutual following check
    @Query("SELECT r FROM GuarantorGuarantorRelation r " +
           "WHERE "
           + "(r.followerGuarantor.guarantorId = :guarantorId1 AND"
           + " r.followingGuarantor.guarantorId = :guarantorId2 AND"
           + " r.status = 'following') " +
           "OR "
           + "(r.followerGuarantor.guarantorId = :guarantorId2 AND"
           + " r.followingGuarantor.guarantorId = :guarantorId1 AND"
           + " r.status = 'following')")
    List<GuarantorGuarantorRelation> findMutualFollowing(@Param("guarantorId1") Integer guarantorId1, @Param("guarantorId2") Integer guarantorId2);

    // Find by status
    List<GuarantorGuarantorRelation> findByStatus(String status);

    // Find by follower guarantor ID
    Page<GuarantorGuarantorRelation> findByFollowerGuarantor_GuarantorId(Integer guarantorId, Pageable pageable);

    // Find by following guarantor ID
    Page<GuarantorGuarantorRelation> findByFollowingGuarantor_GuarantorId(Integer guarantorId, Pageable pageable);

    // Find by follower and following guarantor IDs with pagination
    @Query("SELECT r FROM GuarantorGuarantorRelation r " +
           "WHERE "
           + "r.followerGuarantor.guarantorId = :followerId AND"
           + " r.followingGuarantor.guarantorId = :followingId")
    Page<GuarantorGuarantorRelation> findByFollowerAndFollowing(@Param("followerId") Integer followerId, 
                                                                @Param("followingId") Integer followingId, 
                                                                Pageable pageable);
    
    // Find by ID
    Optional<GuarantorGuarantorRelation> findById(Integer relationId);
}