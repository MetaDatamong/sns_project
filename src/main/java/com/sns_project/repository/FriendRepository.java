package com.sns_project.repository;

import com.sns_project.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    Optional<Friend> findByFollowerAndFollowing(Long followerId, Long followingId);
}