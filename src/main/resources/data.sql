-- User 데이터 삽입

INSERT INTO users (user_id, birthday, nickname, password, role, sex, username)
VALUES
    (1, '1990-01-01', 'gurumi', '$2a$10$9O.xSaZ4AV.CGqEsX2BDquz2bU7ADypJrR5Nd2OmJYeQDnFSlWJWi', 'ROLE_USER', '남성', 'user1'),
    (2, '1992-05-15', 'bassMaster', '$2a$10$crs62DcQkddEO.JfnBHEl.hru/huw15oM5WOj5qAL65Dns.UfPsYa', 'ROLE_USER', '여성', 'user2'),
    (3, '1988-11-30', 'vitamin', '$2a$10$riXcMcUBKGVHopHUPy0LoerWgAiAk7nw9q6jzljqOaetAkunZSBq.', 'ROLE_USER', '남성', 'user3'),
    (4, '1995-03-22', 'bluesky', '$2a$10$abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ12', 'ROLE_USER', '여성', 'user4'),
    (5, '1987-09-10', 'greenleaf', '$2a$10$bcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123', 'ROLE_USER', '남성', 'user5'),
    (6, '1993-12-05', 'redapple', '$2a$10$cdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234', 'ROLE_USER', '여성', 'user6'),
    (7, '1991-06-18', 'purplegrape', '$2a$10$defghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ12345', 'ROLE_USER', '남성', 'user7'),
    (8, '1989-02-14', 'yellowsun', '$2a$10$efghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456', 'ROLE_USER', '여성', 'user8'),
    (9, '1994-08-30', 'orangejuice', '$2a$10$fghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567', 'ROLE_USER', '남성', 'user9'),
    (10, '1986-11-11', 'blackcoffee', '$2a$10$ghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ12345678', 'ROLE_USER', '여성', 'user10'),
    (11, '1997-04-25', 'whitesnow', '$2a$10$hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789', 'ROLE_USER', '남성', 'user11'),
    (12, '1992-07-07', 'graystorm', '$2a$10$ijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890', 'ROLE_USER', '여성', 'user12'),
    (13, '1988-01-01', 'brownearth', '$2a$10$jklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ12345678901', 'ROLE_USER', '남성', 'user13');


-- Friend 테이블 데이터
INSERT INTO friend (friend_id, follower, following)
VALUES
    (1, 13, 2),  -- 사용자 1과 사용자 2는 친구
    (2, 1, 3),
    (3, 1, 5),  -- user1 follows user5
    (4, 1, 8),  -- user1 follows user8
    (5, 2, 1),  -- user2 follows user1
    (6, 2, 3),  -- user2 follows user3
    (7, 2, 7),  -- user2 follows user7
    (8, 3, 4),  -- user3 follows user4
    (9, 3, 6),  -- user3 follows user6
    (10, 4, 1),  -- user4 follows user1
    (11, 4, 5), -- user4 follows user5
    (12, 5, 2), -- user5 follows user2
    (13, 5, 9), -- user5 follows user9
    (14, 6, 3), -- user6 follows user3
    (15, 6, 10),-- user6 follows user10
    (16, 7, 1), -- user7 follows user1
    (17, 7, 8), -- user7 follows user8
    (18, 8, 2), -- user8 follows user2
    (19, 8, 11),-- user8 follows user11
    (20, 9, 3), -- user9 follows user3
    (21, 9, 12),-- user9 follows user12
    (22, 10, 4),-- user10 follows user4
    (23, 10, 7),-- user10 follows user7
    (24, 11, 5),-- user11 follows user5
    (25, 11, 13),-- user11 follows user13
    (26, 12, 6),-- user12 follows user6
    (27, 12, 9),-- user12 follows user9
    (28, 13, 1),-- user13 follows user1
    (29, 13, 10);-- user13 follows user10


INSERT INTO posts (posting_id, content, date_time, post_image_url, sharing_scope, author)
VALUES
    (1, '내가 만든 로고', '2024-10-12 20:30:07.563943', '7f9a824c-1d10-4f55-98da-d8f45dd57251_즐겁다.png', 'PUBLIC', 1),
    (7, '내 웃는 셀카', '2024-10-12 20:30:23.351174', 'c0cde228-7a80-464c-ac47-b39a76e59963_이미지1.png', 'PRIVATE', 1),
    (3, '이번주는 공원 피크닉~', '2024-10-12 20:30:38.759259', '9c56e0be-fa77-40dc-8385-2e5749d5ad4d_이미지2.jfif', 'FRIENDS_ONLY', 1),
    (5, '친구들이랑 락페옴~!', '2024-10-13 16:27:18.497152', '15410f56-567a-4776-ac8c-2760e8576376_썬구리 버전1.png', 'FRIENDS_ONLY', 2),
    (4, '우리집 강아지~~', '2024-10-13 16:27:33.836514', '1acb98a8-70cb-4af1-b690-ea671960d1d7_asdasdadsas.jpg', 'PUBLIC', 2),
    (6, '귀요미', '2024-10-13 16:27:52.438923', '0d72e331-14ac-405a-b4eb-4ced9d52946f_호두.jpg', 'PUBLIC', 2),
    (2, '오늘의 석양 샷~', '2024-10-13 16:29:01.883908', '06ad6e90-5326-40d7-8844-6fc7131f001a_20240806511712.webp', 'FRIENDS_ONLY', 3);
