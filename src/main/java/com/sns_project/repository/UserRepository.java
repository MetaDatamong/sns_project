package com.sns_project.repository;

import com.sns_project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

// 어노테이션이 없어도 JpaRepository를 상속하면 IoC 등록이 자동으로 된다.
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
