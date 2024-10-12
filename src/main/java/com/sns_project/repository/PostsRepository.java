package com.sns_project.repository;

import com.sns_project.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long>, PostsRepositoryCustom {

    @Query(value = "SELECT setval('your_entity_sequence', (SELECT MAX(posting_id) FROM posts))", nativeQuery = true)
    void synchronizeSequence();
}
