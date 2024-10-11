package com.sns_project.repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sns_project.domain.Posts;
import com.sns_project.domain.QFriend;
import com.sns_project.domain.QPosts;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PostsRepositoryCustomImpl implements PostsRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PostsRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Posts> rPosts(Long principalId, Pageable pageable) {
        QPosts posts = QPosts.posts;
        QFriend friend = QFriend.friend;

        List<Posts> content = queryFactory
                .selectFrom(posts)
                .where(posts.userId.in(
                        JPAExpressions
                                .select(friend.userFriendId)
                                .from(friend)
                                .where(friend.userId.eq(principalId))
                ).or(posts.userId.eq(principalId))
                )
                .orderBy(posts.postingId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(posts.count())
                .from(posts)
                .where(posts.userId.in(
                        JPAExpressions
                                .select(friend.userFriendId)
                                .from(friend)
                                .where(friend.userId.eq(principalId))
                ).or(posts.userId.eq(principalId))
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, total);

    }


/*    @Override
    public List<Image> mPopular() {
        QImage image = QImage.image;
        QLikes likes = QLikes.likes;

        NumberPath<Long> likeCount = Expressions.numberPath(Long.class, "likeCount");

        return queryFactory
                .select(image)
                .from(image)
                .leftJoin(likes).on(image.id.eq(likes.imageId))
                .groupBy(image.id)
                .orderBy(likes.imageId.count().desc())
                .fetch();
    }*/
}
