package com.sns_project.controller;

import com.sns_project.config.auth.PrincipalDetails;
import com.sns_project.domain.Friend;
import com.sns_project.domain.User;
import com.sns_project.dto.FriendDto;
import com.sns_project.dto.UserDto;
import com.sns_project.repository.FriendRepository;
import com.sns_project.repository.UserRepository;
import com.sns_project.service.FriendService;
import com.sns_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final UserService userService;
    private final FriendService friendService;

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    public ApiController(UserService userService, FriendService friendService) {
        this.userService = userService;
        this.friendService = friendService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> search(@RequestParam String query) {
        List<UserDto> results = userService.searchUsers(query);
        return ResponseEntity.ok(results);
    }

    @PostMapping("/follow/{followingId}")
    public ResponseEntity<?> followUser(@AuthenticationPrincipal PrincipalDetails userDetails,
                                        @PathVariable Long followingId) {
        if (userDetails == null) {
            return new ResponseEntity<>("User not authenticated", HttpStatus.UNAUTHORIZED);
        }

        Long followerId = userDetails.getUserId();
        FriendDto friendDto = new FriendDto();
        friendDto.setFollowerId(followerId);
        friendDto.setFollowingId(followingId);

        try {
            Friend friend = friendService.followUser(friendDto);
            return new ResponseEntity<>(friend, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/unfollow/{followingId}")
    public ResponseEntity<?> unfollowUser(@AuthenticationPrincipal PrincipalDetails userDetails,
                                          @PathVariable Long followingId) {
        if (userDetails == null) {
            return new ResponseEntity<>("User not authenticated", HttpStatus.UNAUTHORIZED);
        }

        Long followerId = userDetails.getUserId();

        try {
            friendService.unfollowUser(followerId, followingId);
            return new ResponseEntity<>("Unfollowed successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<User>> getFollowers(@PathVariable Long userId) {
        List<User> friendList = friendRepository.findFollowerByFollowingId(userId);
        return ResponseEntity.ok(friendList);
    }
}
