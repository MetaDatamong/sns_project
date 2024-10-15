package com.sns_project.dto;

import com.sns_project.domain.Friend;
import lombok.Data;

@Data
public class FriendDto {
    private Long followerId;
    private Long followingId;

    public Friend toEntity() {
        return Friend.builder()
                .follower(followerId)
                .following(followingId)
                .build();
    }
}
