INSERT INTO `store` (`id`, `name`, `address`)
VALUES
    (1, '맥도날드 강남구청역', '서울 강남구 도산대로 407 맥도날드'),
    (2, 'BHC 강남구청역점', '서울 강남구 학동로56길 26'),
    (3, '청담보쌈', '서울 강남구 도산대로 407 맥도날드'),
    (4, '이디야커피 건설회관', '서울 강남구 언주로 711 1층'),
    (5, '다왕래 장어구이 논현점', '서울 강남구 선릉로129길 21 2층')
;
ALTER TABLE `store` ALTER COLUMN `id` RESTART WITH (SELECT COUNT(*) FROM `store`) + 1;

INSERT INTO `menu` (`id`, `name`, `price`, `store_id`)
VALUES
    (1, '빅맥 세트', 6500, 1),
    (2, '1955 버거 세트', 6500, 1),
    (3, '맥스파이시 상하이 버거 라지 세트', 7500, 1),

    (4, '레드킹 폭립', 28000, 2),
    (5, '골드킹 콤보', 20000, 2),
    (6, '뿌링클 콤보', 20000, 2),
    (7, '후라이드', 19000, 2),

    (8, '해물파전', 23000, 3),
    (9, '김치전', 18000, 3),
    (10, '감자전', 20000, 3),
    (11, '족발', 35000, 3),
    (12, '보쌈', 38000, 3),

    (13, '아메리카노', 2500, 4),
    (14, '바닐라라떼', 3800, 4),
    (15, '크러플', 3500, 4),
    (16, '크로아상', 4000, 4),
    (17, '초콜릿 마카롱', 2500, 4),

    (18, '바다장어구이', 40000, 5),
    (19, '양념장어구이', 42000, 5),
    (20, '장어철판볶음', 20000, 5),
    (21, '장어회', 60000, 5),
    (22, '특선 장어탕', 20000, 5),
    (23, '장어숙회초무침', 40000, 5)
;
ALTER TABLE `menu` ALTER COLUMN `id` RESTART WITH (SELECT COUNT(*) FROM `menu`) + 1;

INSERT INTO `account` (`id`, `email`, `name`, `authority`, `address`, `password`)
VALUES
    (1, 'tester1@gmail.com', 'tester1', 0, '서울 강남구 학동로 405 청담래미안아파트 101동 101호', '$2a$10$LTO75nvD2rLM1/KueuH.ROBtZZ9leqyEwqxMziioW3Wbg3ueeWxQ.'),  -- password: aaaAAA111!!!
    (2, 'tester2@naver.com', 'tester2', 0, '서울 강남구 언주로146길 18 동현아파트 4동 301호', '$2a$10$zAcvsCpWHpWkbVp7SUsaUuF450aAbmdw90nSBRlWyMzceJopbym7u'),  -- password: bbbBBB222!!!
    (3, 'tester3@gmail.com', 'tester3', 0, '서울 강남구 언주로130길 30 동양파라곤아파트 104동 505호', '$2a$10$B4o4Pc7jjgEXSWc5DBA2b.nhykl3NZDwbNdsARKTthMEMyXY79dgS'),  -- password: cccCCC333!!!
    (4, 'tester4@daum.net', 'tester4', 0, '서울 강남구 선릉로137길 21 라이헤스빌 1호', '$2a$10$2NUfCKjvUMXb4UGWtdUojO2w1DrhKsMoqnX7nYbtj9iXQaEwDeHCy')   -- password: dddDDD444!!!
;
ALTER TABLE `account` ALTER COLUMN `id` RESTART WITH (SELECT COUNT(*) FROM `account`) + 1;

INSERT INTO `delivery` (`id`, `status`, `customer_id`, `ordered_date`, `store_id`) VALUES (1, 3, 1, TIMESTAMP '2022-10-20 22:00:10.111', 1);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (3, 1, 1);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (2, 1, 2);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (4, 1, 3);

INSERT INTO `delivery` (`id`, `status`, `customer_id`, `ordered_date`, `store_id`) VALUES (2, 3, 1, TIMESTAMP '2022-11-01 20:10:16.322', 2);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (3, 2, 4);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (2, 2, 5);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (4, 2, 6);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (4, 2, 7);

INSERT INTO `delivery` (`id`, `status`, `customer_id`, `ordered_date`, `store_id`) VALUES (3, 3, 1, TIMESTAMP '2022-12-20 18:04:40.424', 3);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (3, 3, 8);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (4, 3, 9);

INSERT INTO `delivery` (`id`, `status`, `customer_id`, `ordered_date`, `store_id`) VALUES (4, 3, 1, TIMESTAMP '2022-12-21 18:04:20.424', 4);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (3, 4, 13);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (2, 4, 14);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (4, 4, 15);

INSERT INTO `delivery` (`id`, `status`, `customer_id`, `ordered_date`, `store_id`) VALUES (5, 3, 1, TIMESTAMP '2022-12-22 18:04:10.424', 5);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (3, 5, 18);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (2, 5, 19);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (4, 5, 20);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (4, 5, 21);

INSERT INTO `delivery` (`id`, `status`, `customer_id`, `ordered_date`, `store_id`) VALUES (6, 3, 1, TIMESTAMP '2022-12-23 19:32:16.133', 1);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (3, 6, 1);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (2, 6, 2);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (4, 6, 3);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (4, 6, 1);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (4, 6, 1);

INSERT INTO `delivery` (`id`, `status`, `customer_id`, `ordered_date`, `store_id`) VALUES (7, 0, 1, TIMESTAMP '2022-12-24 22:00:10.322', 2);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (3, 7, 6);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (2, 7, 7);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (4, 7, 5);

INSERT INTO `delivery` (`id`, `status`, `customer_id`, `ordered_date`, `store_id`) VALUES (8, 3, 2, TIMESTAMP '2022-01-17 22:00:10.111', 3);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (3, 8, 8);

INSERT INTO `delivery` (`id`, `status`, `customer_id`, `ordered_date`, `store_id`) VALUES (9, 3, 2, TIMESTAMP '2022-05-21 20:10:16.322', 4);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (3, 9, 13);

INSERT INTO `delivery` (`id`, `status`, `customer_id`, `ordered_date`, `store_id`) VALUES (10, 1, 2, TIMESTAMP '2022-10-01 19:32:16.133', 5);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (3, 10, 23);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (2, 10, 21);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (4, 10, 22);

INSERT INTO `delivery` (`id`, `status`, `customer_id`, `ordered_date`, `store_id`) VALUES (11, 3, 3, TIMESTAMP '2022-06-19 12:00:10.111', 1);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (3, 11, 3);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (2, 11, 2);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (4, 11, 1);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (4, 11, 1);

INSERT INTO `delivery` (`id`, `status`, `customer_id`, `ordered_date`, `store_id`) VALUES (12, 3, 3, TIMESTAMP '2022-06-20 22:00:10.111', 2);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (3, 12, 7);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (2, 12, 5);

INSERT INTO `delivery` (`id`, `status`, `customer_id`, `ordered_date`, `store_id`) VALUES (13, 3, 3, TIMESTAMP '2022-08-22 22:00:10.111', 3);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (3, 13, 8);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (2, 13, 10);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (4, 13, 12);

INSERT INTO `delivery` (`id`, `status`, `customer_id`, `ordered_date`, `store_id`) VALUES (14, 2, 3, TIMESTAMP '2022-09-01 22:00:10.111', 4);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (3, 14, 15);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (2, 14, 16);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (4, 14, 13);
INSERT INTO `delivery_detail` (`count`, `delivery_id`, `menu_id`) VALUES (4, 14, 17);

ALTER TABLE `delivery` ALTER COLUMN `id` RESTART WITH (SELECT COUNT(*) FROM `delivery`) + 1;
ALTER TABLE `delivery_detail` ALTER COLUMN `id` RESTART WITH (SELECT COUNT(*) FROM `delivery_detail`) + 1;