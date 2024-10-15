package com.sns_project.repository;

import com.sns_project.domain.Friend;
import com.sns_project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    Optional<Friend> findByFollowerAndFollowing(Long followerId, Long followingId);

    @Query("SELECT u FROM User u JOIN Friend f ON u.userId = f.follower WHERE f.following = :followingId")
    List<User> findFollowerByFollowingId(@Param("followingId") Long followingId);

    @Query("SELECT u FROM User u JOIN Friend f ON u.userId = f.following WHERE f.follower = :followerId")
    List<User> findFollowingByFollowingId(@Param("followerId") Long followerId);

}
