package com.sns_project.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;

    @Column(unique = true, length = 20)
    private String username;

    @Column(nullable = false)
    private String password;

    private String birthday;

    @Column(nullable = false)
    private String nickname;
    private Sex sex;
    private String role;

    public enum Sex {
        MALE("남"),
        FEMALE("여"),
        NONE("선택하지 않음");

        // Enum에 상수를 입력하기 위해 추가한 과정. https://rachel0115.tistory.com/entry/Java-%EC%97%B4%EA%B1%B0%ED%98%95-%ED%81%B4%EB%9E%98%EC%8A%A4-Enum
        private final String message;
        Sex(String s) {  // Enum 생성자
            this.message = s;
        }
        public String getValue() { // Enum 상수 값을 불러오기 위한 메소드
            return message;
        }
    }


}
