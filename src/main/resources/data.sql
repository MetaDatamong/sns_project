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
-- 사용자 1의 게시물
(1, '오늘은 날씨가 정말 좋네요. 공원에서 산책하기 좋은 날입니다.', '2023-03-15 10:00:00', 'https://example.com/images/park.jpg', 0, 1),
(2, '새로운 요리 레시피를 시도해봤어요. 여러분도 한번 해보세요!', '2023-03-16 14:30:00', 'https://example.com/images/recipe.jpg', 1, 1),
(3, '오랜만에 친구들과 만나 즐거운 시간을 보냈습니다.', '2023-03-17 20:00:00', 'https://example.com/images/friends.jpg', 2, 1),

-- 사용자 2의 게시물
(4, '새 책을 샀어요. 이번 주말에 읽을 예정입니다.', '2023-03-18 09:15:00', 'https://example.com/images/book.jpg', 0, 2),
(5, '회사 프로젝트가 성공적으로 마무리되었습니다!', '2023-03-19 17:45:00', 'https://example.com/images/project.jpg', 1, 2),
(6, '가족과 함께한 주말 여행, 정말 좋았어요.', '2023-03-20 11:30:00', 'https://example.com/images/trip.jpg', 2, 2),

-- 사용자 3의 게시물
(7, '새로운 취미로 기타를 배우기 시작했습니다.', '2023-03-21 13:00:00', 'https://example.com/images/guitar.jpg', 0, 3),
(8, '오늘 본 영화 정말 감동적이었어요. 추천합니다!', '2023-03-22 22:00:00', 'https://example.com/images/movie.jpg', 1, 3),
(9, '드디어 오랫동안 준비한 마라톤 대회에 참가했어요.', '2023-03-23 18:30:00', 'https://example.com/images/marathon.jpg', 2, 3);