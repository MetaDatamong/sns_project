package com.sns_project.repository;

import com.sns_project.domain.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostsRepositoryCustom {
    Page<Posts> mStory(Long principalId, Pageable pageable);
    //List<Posts> mPopular();
}
