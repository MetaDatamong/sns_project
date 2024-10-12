-- User 데이터 삽입

INSERT INTO users (user_id, birthday, nickname, password, role, sex, username)
VALUES
    (1, '1990-01-01', 'gurumi', '$2a$10$9O.xSaZ4AV.CGqEsX2BDquz2bU7ADypJrR5Nd2OmJYeQDnFSlWJWi', 'USER', '남성', '11'),
    (2, '1992-05-15', 'bassMaster', '$2a$10$crs62DcQkddEO.JfnBHEl.hru/huw15oM5WOj5qAL65Dns.UfPsYa', 'USER', '여성', '22'),
    (3, '1988-11-30', 'vitamin', '$2a$10$riXcMcUBKGVHopHUPy0LoerWgAiAk7nw9q6jzljqOaetAkunZSBq.', 'USER', '남성', '33');


-- Friend 테이블 데이터
INSERT INTO friend (friend_id, friend_status, user_friend_id, user_id)
VALUES
    (1, 1, 2, 1),  -- 사용자 1과 사용자 2는 친구
    (2, 1, 1, 2),  -- 사용자 2와 사용자 1은 친구 (양방향 관계)
    (3, 1, 3, 1),  -- 사용자 1과 사용자 3은 친구
    (4, 1, 1, 3);  -- 사용자 3과 사용자 1은 친구 (양방향 관계)

-- posts 테이블 데이터

INSERT INTO posts (posting_id, content, date_time, post_image_url, sharing_scope, author)
VALUES
    (1, '11', '2024-10-12 20:30:07.563943', '7f9a824c-1d10-4f55-98da-d8f45dd57251_즐길다.png', 'PUBLIC', 1),
    (2, '웃음', '2024-10-12 20:30:23.351174', 'c0cde228-7a80-464c-ac47-b39a76e59963_이미지1.png', 'PRIVATE', 1),
    (3, '공원감', '2024-10-12 20:30:38.759259', '9c56e0be-fa77-40dc-8385-2e5749d5ad4d_이미지2.jfif', 'FRIENDS_ONLY', 1);

/*-- 사용자 2의 게시물
(4, '새 책을 샀어요. 이번 주말에 읽을 예정입니다.', '2023-03-18 09:15:00', 'https://example.com/images/book.jpg', 0, 2),
(5, '회사 프로젝트가 성공적으로 마무리되었습니다!', '2023-03-19 17:45:00', 'https://example.com/images/project.jpg', 1, 2),
(6, '가족과 함께한 주말 여행, 정말 좋았어요.', '2023-03-20 11:30:00', 'https://example.com/images/trip.jpg', 2, 2),

-- 사용자 3의 게시물
(7, '새로운 취미로 기타를 배우기 시작했습니다.', '2023-03-21 13:00:00', 'https://example.com/images/guitar.jpg', 0, 3),
(8, '오늘 본 영화 정말 감동적이었어요. 추천합니다!', '2023-03-22 22:00:00', 'https://example.com/images/movie.jpg', 1, 3),
(9, '드디어 오랫동안 준비한 마라톤 대회에 참가했어요.', '2023-03-23 18:30:00', 'https://example.com/images/marathon.jpg', 2, 3);*/