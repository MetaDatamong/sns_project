package com.sns_project.controller;

import com.sns_project.domain.User;
import com.sns_project.dto.UserDto;
import com.sns_project.repository.UserRepository;
import com.sns_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class ApiController {

    private final UserService userService;

    @Autowired
    public ApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> search(@RequestParam String query) {
        List<UserDto> results = userService.searchUsers(query);
        return ResponseEntity.ok(results);
    }
}
