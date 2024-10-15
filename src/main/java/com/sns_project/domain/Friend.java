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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friendId_seq")
    @SequenceGenerator(name = "friendId_seq", sequenceName = "friendId_seq", allocationSize = 1, initialValue = 30)
    private Long friendId;

    // 이 사람이 구독함
    private Long follower;

    // 구독 당하는 사람
    private Long following;

    private FriendStatus friendStatus;

    public enum FriendStatus {
        PENDING,
        ACCEPTED,
        BLOCKED
    }

}
