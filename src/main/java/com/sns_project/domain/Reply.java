package com.sns_project.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long replyId;

    private String content;
    private LocalDateTime dateTime;

    @Column(name = "author")
    private Long userId;

    private Long postingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_reply_id")
    @JsonBackReference //엔티티 자체에 직접 사용하는 것은 권장되지 않습니다. 대신 DTO를 사용하거나 별도의 직렬화 설정을 하는 것이 좋습니다.
    private Reply parentReply;

    @Builder.Default // Lombok의 @Builder 어노테이션과 함께 사용될 때 의미가 있습니다. 여기서는 필드 초기화로 충분합니다.
    @OneToMany (mappedBy = "parentReply", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Reply> subReplyList = new ArrayList<>();


}
