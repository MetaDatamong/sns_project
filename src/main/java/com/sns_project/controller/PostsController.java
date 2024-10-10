package com.sns_project.controller;

import com.sns_project.domain.Posts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostsController {

    @GetMapping("/posting")
    public String getPosting(Model model) {
        // api는 데이터를 리턴하는 서버!!
        List<Posts> posts = post;
        model.addAttribute("images", images);

        return "image/popular";
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
