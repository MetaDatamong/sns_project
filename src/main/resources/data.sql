-- User 데이터 삽입

INSERT INTO users (user_id, birthday, nickname, password, role, sex, username)
VALUES
    (1, '1990-01-01', 'gurumi', '$2a$10$9O.xSaZ4AV.CGqEsX2BDquz2bU7ADypJrR5Nd2OmJYeQDnFSlWJWi', 'ROLE_USER', '남성', 'user1'),
    (2, '1992-05-15', 'bassMaster', '$2a$10$crs62DcQkddEO.JfnBHEl.hru/huw15oM5WOj5qAL65Dns.UfPsYa', 'ROLE_USER', '여성', 'user2'),
    (3, '1988-11-30', 'vitamin', '$2a$10$riXcMcUBKGVHopHUPy0LoerWgAiAk7nw9q6jzljqOaetAkunZSBq.', 'ROLE_USER', '남성', 'user3');


-- Friend 테이블 데이터
/*INSERT INTO friend (friend_id, follower, following)
VALUES
    (1, 1, 2),  -- 사용자 1과 사용자 2는 친구
    (2, 1, 3);  -- 사용자 2와 사용자 1은 친구 (양방향 관계)*/
/*    (3, 1, 3, 1),  -- 사용자 1과 사용자 3은 친구
    (4, 1, 1, 3);  -- 사용자 3과 사용자 1은 친구 (양방향 관계)*/

-- posts 테이블 데이터

INSERT INTO posts (posting_id, content, date_time, post_image_url, sharing_scope, author)
VALUES
    (1, '내가 만든 로고', '2024-10-12 20:30:07.563943', '7f9a824c-1d10-4f55-98da-d8f45dd57251_즐겁다.png', 'PUBLIC', 1),
    (7, '내 웃는 셀카', '2024-10-12 20:30:23.351174', 'c0cde228-7a80-464c-ac47-b39a76e59963_이미지1.png', 'PRIVATE', 1),
    (3, '이번주는 공원 피크닉~', '2024-10-12 20:30:38.759259', '9c56e0be-fa77-40dc-8385-2e5749d5ad4d_이미지2.jfif', 'FRIENDS_ONLY', 1),
    (5, '친구들이랑 락페옴~!', '2024-10-13 16:27:18.497152', '15410f56-567a-4776-ac8c-2760e8576376_썬구리 버전1.png', 'FRIENDS_ONLY', 2),
    (4, '우리집 강아지~~', '2024-10-13 16:27:33.836514', '1acb98a8-70cb-4af1-b690-ea671960d1d7_asdasdadsas.jpg', 'PUBLIC', 2),
    (6, '귀요미', '2024-10-13 16:27:52.438923', '0d72e331-14ac-405a-b4eb-4ced9d52946f_호두.jpg', 'PUBLIC', 2),
    (2, '펜타포트 락 페스티벌~', '2024-10-13 16:29:01.883908', '06ad6e90-5326-40d7-8844-6fc7131f001a_20240806511712.webp', 'FRIENDS_ONLY', 3);
