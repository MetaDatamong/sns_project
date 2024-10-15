package com.sns_project.controller;

import com.sns_project.config.auth.PrincipalDetails;
import com.sns_project.domain.Posts;
import com.sns_project.domain.User;
import com.sns_project.repository.FriendRepository;
import com.sns_project.repository.PostsRepository;
import com.sns_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private PostsRepository postsRepository;

    @GetMapping("/user/{userId}")
    public String getProfile(@AuthenticationPrincipal PrincipalDetails userDetails, @PathVariable Long userId, Model model) {

        model.addAttribute("principal", userDetails);
        User user = userRepository.findById(userId).orElse(null);
        model.addAttribute("user", user);
        // 해당 프로필 주인인지 확인하는 로직 추가
            // 해당 프로필이 주인인지 확인.
            // 맞다면 콜렉션으로 만들어서 isOwner라는 객체를 user와 함께 담아서 model로 보내기.

        List<User> followerList = friendRepository.findFollowerByFollowingId(user.getUserId());
        List<User> followingList = friendRepository.findFollowingByFollowingId(user.getUserId());

        int followerCnt = followerList.size();
        int followingCnt = followingList.size();

        Map<String, Object> userInfo = new HashMap<>();

        boolean isOwner = false;
        if (user != null && userDetails != null && userDetails.getUser() != null) {
            isOwner = userDetails.getUserId().equals(user.getUserId());
        }

        userInfo.put("user", user);
        userInfo.put("isOwner", isOwner);
        userInfo.put("followerCnt", followerCnt);
        userInfo.put("followingCnt", followingCnt);

        model.addAttribute("userInfo", userInfo);

        List<Posts> posts = postsRepository.findAllByUserUserId(userId);

        model.addAttribute("posts", posts);

        return "user/profile";
    }

}
