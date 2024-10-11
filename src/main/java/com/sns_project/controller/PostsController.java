package com.sns_project.controller;

import com.sns_project.domain.Posts;
import com.sns_project.repository.PostsRepository;
import com.sns_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostsController {

    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    /*    @GetMapping("/posting")
    public String getPosting(int principalId, Pageable pageable) {
        // api는 데이터를 리턴하는 서버!!

        Long longPrincipalId = Long.valueOf(principalId);

        Page<Posts> images = postsRepository.rPosts(longPrincipalId, pageable);

        //return "image/popular";
        return "auth/signin";
    }*/

    @GetMapping("/posting")
    @ResponseBody
    public String getPosting(@AuthenticationPrincipal UserDetails userDetails, Pageable pageable) {

        String username = userDetails.getUsername();

        Long principalId = userRepository.findByUsername(username).getUserId();

        Page<Posts> posts = postsRepository.rPosts(principalId, pageable);

        StringBuilder response = new StringBuilder();
        response.append("<html><body>");
        response.append("<h1>Posts</h1>");

        for (Posts post : posts.getContent()) {
            response.append("<div>");
            response.append("<h2>Post ID: ").append(post.getPostingId()).append("</h2>");
            response.append("<p>Content: ").append(post.getContent()).append("</p>");
            response.append("<p>Date: ").append(post.getDateTime()).append("</p>");
            response.append("<p>Image URL: ").append(post.getPostImageUrl()).append("</p>");
            response.append("<p>Sharing Scope: ").append(post.getSharingScope()).append("</p>");
            response.append("<p>Author ID: ").append(post.getUserId()).append("</p>");
            response.append("</div><hr>");
        }

        // Pagination information
        response.append("<p>Page ").append(posts.getNumber() + 1)
                .append(" of ").append(posts.getTotalPages()).append("</p>");

        response.append("</body></html>");

        return response.toString();
    }


  /*  @GetMapping(value = {"", "/"})
    public String showPosts(Model model) {
        // 로그인 확인

        // 로그인 유저 id 가져오기

        // 친구들의 posting 가져오기
            // 로그인 유저의 친구 리스트 가져오기
            // 친구들의 포스팅 리스트 가져오기
            //

    }*/

}
