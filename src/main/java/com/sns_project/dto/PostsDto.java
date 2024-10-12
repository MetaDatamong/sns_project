package com.sns_project.dto;

import com.sns_project.domain.Posts;
import com.sns_project.domain.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostsDto {

    private MultipartFile file;
    private String content;

    public Posts toEntity(String postImageUrl, User user) {
        return Posts.builder()
                .content(content)
                .postImageUrl(postImageUrl)
                .user(user)
                .build();
    }
}
