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
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long postingId;

    //private String title;
    private String content;
    private LocalDateTime dateTime;
    private SharingScope sharingScope;
    private String postImageUrl;

    @Column(name = "author")
    private Long userId;

    @Transient // DB에 칼럼이 만들어지지 않는다.
    private boolean likeState;

    @Transient
    private int likeCount;

    @PrePersist
    public void createDate() {
        this.dateTime = LocalDateTime.now();
    }

    public enum SharingScope {
        PUBLIC,
        PRIVATE,
        FRIENDS_ONLY;
    }
}
