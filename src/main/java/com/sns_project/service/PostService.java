package com.sns_project.service;

import com.sns_project.domain.Posts;
import com.sns_project.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    @Autowired
    PostsRepository postsRepository;

/*    @Transactional(readOnly = true)
    public List<Posts> popularPosts() {
        return postsRepository.mStory();
    }*/
}
