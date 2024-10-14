package com.sns_project.controller;

import com.sns_project.domain.User;
import com.sns_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{userId}")
    public String getProfile(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long userId, Model model) {

        model.addAttribute("principal", userDetails);
        User user = userRepository.findById(userId).orElse(null);
        model.addAttribute("user", user);

        return "user/profile";

    }
}
