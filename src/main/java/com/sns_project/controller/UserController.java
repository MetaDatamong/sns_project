package com.sns_project.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @GetMapping("/user/{userId}")
    public String getProfile(@PathVariable Long userId, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("principal", userDetails);

        return "user/profile";

    }
}
