package com.sns_project.service;

import com.sns_project.domain.User;
import com.sns_project.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service // 1. IoC  2. 트랜잭션 관리
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional // Write(Insert, Update, Delete)
    public User singUp(User user) throws RuntimeException {
        // 회원가입 진행
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("ROLE_USER"); // 관리자 ROLE_ADMIN

        //User userEntity = userRepository.save(user);
        User userEntity = null;
        userEntity = userRepository.save(user);

        return userEntity;
    }

}