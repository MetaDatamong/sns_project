package com.sns_project.repository;

import com.sns_project.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long>, PostsRepositoryCustom {
    // userId 로 모든 게시글 불러오기
    List<Posts> findAllByUserUserId(Long userId);

    @Query(value = "SELECT setval('your_entity_sequence', (SELECT MAX(posting_id) FROM posts))", nativeQuery = true)
    void synchronizeSequence();
}
