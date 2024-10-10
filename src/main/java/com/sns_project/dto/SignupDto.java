package com.sns_project.dto;

import com.sns_project.domain.User;
import lombok.Data;

@Data // Getter, Setter
public class SignupDto {
    private String username;
    private String password;
    private String birthday;
    private String nickname;
    private String sex;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .birthday(birthday)
                .nickname(nickname)
                .sex(sex)
                .build();
    }
}