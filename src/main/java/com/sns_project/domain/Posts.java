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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "your_entity_seq")
    @SequenceGenerator(name = "your_entity_seq", sequenceName = "your_entity_sequence", allocationSize = 1, initialValue = 9)
    private Long postingId;

    //private String title;
    private String content;
    private LocalDateTime dateTime;
    private String sharingScope;
    private String postImageUrl;

/*    @Column(name = "author")
    private Long userId;*/

    @JoinColumn(name = "author")
    @ManyToOne(fetch = FetchType.EAGER) // 이미지를 select하면 조인해서 User정보를 같이 들고옴
    private User user;

    @Transient // DB에 칼럼이 만들어지지 않는다.
    private boolean likeState;

    @Transient
    private int likeCount;

    @PrePersist
    public void createDate() {
        this.dateTime = LocalDateTime.now();
    }

/*    public enum SharingScope {
        PUBLIC,
        PRIVATE,
        FRIENDS_ONLY;
    }*/
}
