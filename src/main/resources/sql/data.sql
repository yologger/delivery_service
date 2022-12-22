INSERT INTO `store` (`name`, `address`)
VALUES
    ('맥도날드 강남구청역', '서울 강남구 도산대로 407 맥도날드'),
    ('BHC 강남구청역점', '서울 강남구 학동로56길 26'),
    ('청담보쌈', '서울 강남구 도산대로 407 맥도날드'),
    ('이디야커피 건설회관', '서울 강남구 언주로 711 1층'),
    ('다왕래 장어구이 논현점', '서울 강남구 선릉로129길 21 2층')
;

INSERT INTO `menu` (`name`, `price`, `store_id`)
VALUES
    ('빅맥 세트', 6500, (SELECT `id` from `store` WHERE name='맥도날드 강남구청역')),
    ('빅맥 세트', 6500, (SELECT `id` from `store` WHERE name='맥도날드 강남구청역')),
    ('맥스파이시 상하이 버거 라지 세트', 7500, (SELECT `id` from `store` WHERE name='맥도날드 강남구청역')),
    ('1955 버거 세트', 7500, (SELECT `id` from `store` WHERE name='맥도날드 강남구청역')),
    ('레드킹 폭립', 28000, (SELECT `id` from `store` WHERE name='BHC 강남구청역점')),
    ('골드킹 콤보', 20000, (SELECT `id` from `store` WHERE name='BHC 강남구청역점')),
    ('뿌링클 콤보', 20000, (SELECT `id` from `store` WHERE name='BHC 강남구청역점')),
    ('후라이드', 19000, (SELECT `id` from `store` WHERE name='BHC 강남구청역점')),
    ('해물파전', 23000, (SELECT `id` from `store` WHERE name='청담보쌈')),
    ('김치전', 18000, (SELECT `id` from `store` WHERE name='청담보쌈')),
    ('감자전', 20000, (SELECT `id` from `store` WHERE name='청담보쌈')),
    ('족발', 35000, (SELECT `id` from `store` WHERE name='청담보쌈')),
    ('보쌈', 38000, (SELECT `id` from `store` WHERE name='청담보쌈')),
    ('아메리카노', 2500, (SELECT `id` from `store` WHERE name='이디야커피 건설회관')),
    ('바닐라라떼', 3800, (SELECT `id` from `store` WHERE name='이디야커피 건설회관')),
    ('크러플', 3500, (SELECT `id` from `store` WHERE name='이디야커피 건설회관')),
    ('크로아상', 4000, (SELECT `id` from `store` WHERE name='이디야커피 건설회관')),
    ('초콜릿 마카롱', 2500, (SELECT `id` from `store` WHERE name='이디야커피 건설회관')),
    ('바다장어구이', 40000, (SELECT `id` from `store` WHERE name='다왕래 장어구이 논현점')),
    ('양념장어구이', 42000, (SELECT `id` from `store` WHERE name='다왕래 장어구이 논현점')),
    ('장어철판볶음', 20000, (SELECT `id` from `store` WHERE name='다왕래 장어구이 논현점')),
    ('장어회', 60000, (SELECT `id` from `store` WHERE name='다왕래 장어구이 논현점')),
    ('특선 장어탕', 20000, (SELECT `id` from `store` WHERE name='다왕래 장어구이 논현점')),
    ('장어숙회초무침', 40000, (SELECT `id` from `store` WHERE name='다왕래 장어구이 논현점'))
;