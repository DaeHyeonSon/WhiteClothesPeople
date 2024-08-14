package com.whitepeoples.wooso.dao.relation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.whitepeoples.wooso.model.entity.relations.UserUserRelation;



@Repository
public interface UserUserRelationRepository extends JpaRepository<UserUserRelation, Integer> {

    @Query("SELECT r FROM UserUserRelation r " +
           "WHERE "
           + "(r.followerUser.userId = :userId1 AND"
           + " r.followingUser.userId = :userId2 AND"
           + " r.status = 'following') " +
           "OR "
           + "(r.followerUser.userId = :userId2 AND"
           + " r.followingUser.userId = :userId1 AND"
           + " r.status = 'following')")
    List<UserUserRelation> findMutualFollowing(@Param("userId1") Integer userId1, @Param("userId2") Integer userId2);
    
    // Find by status
    List<UserUserRelation> findByStatus(String status);

    // Find by follower user ID
    Page<UserUserRelation> findByFollowerUser_UserId(Integer userId, Pageable pageable);

    // Find by following user ID
    Page<UserUserRelation> findByFollowingUser_UserId(Integer userId, Pageable pageable);

    // Find by follower and following user IDs with pagination
    @Query("SELECT r FROM UserUserRelation r " +
           "WHERE"
           + "r.followerUser.userId = :followerId AND "
           + "r.followingUser.userId = :followingId")
    Page<UserUserRelation> findByFollowerAndFollowing(@Param("followerId") Integer followerId, 
                                                      @Param("followingId") Integer followingId, 
                                                      Pageable pageable);
    
    // Find by ID
    Optional<UserUserRelation> findById(Integer relationId);
}