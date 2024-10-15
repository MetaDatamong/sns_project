package com.sns_project.service;
import com.sns_project.domain.Friend;
import com.sns_project.dto.FriendDto;
import com.sns_project.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class FriendService {

    private final FriendRepository friendRepository;

    @Autowired
    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @Transactional
    public Friend followUser(FriendDto friendDto) throws Exception {
        // 자기 자신을 팔로우하는 경우 예외 처리
        if (friendDto.getFollowerId().equals(friendDto.getFollowingId())) {
            throw new Exception("You cannot follow yourself");
        }

        // 이미 팔로우한 경우 예외 처리
        Optional<Friend> existingFriend = friendRepository.findByFollowerAndFollowing(
                friendDto.getFollowerId(), friendDto.getFollowingId());
        if (existingFriend.isPresent()) {
            throw new Exception("You are already following this user");
        }

        Friend friend = friendDto.toEntity();
        friend.setFriendStatus(Friend.FriendStatus.ACCEPTED); // 즉시 수락 상태로 설정
        return friendRepository.save(friend);
    }

    @Transactional
    public void unfollowUser(Long followerId, Long followingId) throws Exception {
        Optional<Friend> friend = friendRepository.findByFollowerAndFollowing(followerId, followingId);
        if (friend.isPresent()) {
            friendRepository.delete(friend.get());
        } else {
            throw new Exception("You are not following this user");
        }
    }

    public Friend.FriendStatus getFriendStatus(Long userId1, Long userId2) throws Exception {
        Optional<Friend> friend = friendRepository.findByFollowerAndFollowing(userId1, userId2);
        return friend.map(Friend::getFriendStatus).orElse(null);
    }
}
