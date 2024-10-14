package com.sns_project.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String nickname;

    public UserDto(Long id, String username, String nickname) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
    }

}
