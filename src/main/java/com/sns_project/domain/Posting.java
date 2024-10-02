package com.sns_project.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Posting {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long postingId;

    private String title;
    private String content;
    private LocalDateTime dateTime;
    private SharingScope sharingScope;

    @Column(name = "author")
    private Long userId;

    public enum SharingScope {
        PUBLIC,
        PRIVATE,
        FRIENDS_ONLY;
    }
}
