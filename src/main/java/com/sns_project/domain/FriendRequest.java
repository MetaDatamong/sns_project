package com.sns_project.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class FriendRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long friendRequestId;

    private Long requestSenderId;
    private Long requestReceiverId;
    private LocalDateTime requestDate;


}
