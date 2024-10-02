package com.sns_project.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long friendId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_friend_id")
    private Long userFriendId;

    private FriendStatus friendStatus;

    public enum FriendStatus {
        PENDING,
        ACCEPTED,
        BLOCKED
    }

}
