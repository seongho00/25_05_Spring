DROP DATABASE IF EXISTS 25_05_Spring;
CREATE DATABASE 25_05_Spring;
USE 25_05_Spring;

# 게시글 테이블 생성
CREATE TABLE article (
                         id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         regDate DATETIME NOT NULL,
                         updateDate DATETIME NOT NULL,
                         title CHAR(100) NOT NULL,
                         `body` TEXT NOT NULL,
                         memberId INT(10) UNSIGNED NOT NULL
);

CREATE TABLE `member` (
                         id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         regDate DATETIME NOT NULL,
                         updateDate DATETIME NOT NULL,
                         loginId CHAR(100) NOT NULL,
                         loginPw CHAR(100) NOT NULL,
                         email CHAR(100) NOT NULL,
                         `authLevel` SMALLINT(2) UNSIGNED DEFAULT 3 COMMENT '권한레벨 (3: 일반, 7: 관리자)',
                         `name` CHAR(100) NOT NULL,
                         delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '탈퇴 여부 (0: 탈퇴 전 , 1: 탈퇴 후)',
                         delDate DATETIME COMMENT '탈퇴 날짜'
);



# 게시글 테스트 데이터 생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목1',
`body` = '내용1',
memberId = '1';


INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목2',
`body` = '내용2',
memberId = '1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목3',
`body` = '내용2',
memberId = '2';

# 멤버 테스트 데이터 생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = "test1",
loginPw = "test1",
`name` = "이름1",
email = "test1@gmail.com";

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = "test2",
loginPw = "test2",
`name` = "이름2",
email = "test2@gmail.com";

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = "test3",
loginPw = "test3",
`name` = "이름3",
email = "test3@gmail.com";

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = "123",
loginPw = "123",
`authLevel` = 7,
`name` = "123",
email = "123@gmail.com";


SELECT *
FROM article
ORDER BY id DESC;

SELECT *
FROM `member`

SELECT * FROM `member`   WHERE loginId = 123

SELECT * 
FROM `member`
WHERE `name` = '이름2' AND email = '이름2'


######################################################################

# 게시글 데이터 대량 생성
INSERT INTO article
SET regDate = NOW(),
title = CONCAT('제목',SUBSTRING(RAND() * 1000 FROM 1 FOR 2)),
`body` = CONCAT('내용',SUBSTRING(RAND() * 1000 FROM 1 FOR 2)),
memberId = CEILING(RAND()*3);

# 회원 데이터 대량 생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = CONCAT('loginId ',SUBSTRING(RAND() * 1000 FROM 1 FOR 2)),
loginPw = CONCAT('loginPw ',SUBSTRING(RAND() * 1000 FROM 1 FOR 2)),
`name` = CONCAT('name ',SUBSTRING(RAND() * 1000 FROM 1 FOR 2));



SELECT COUNT(*) > 0
FROM `member`
WHERE loginId = 'test2';

SELECT 1 + 1;
SELECT 1 >= 1;

SELECT COUNT(*) > 0 FROM `member` WHERE loginId = 'test3';

SELECT NOW();

SELECT '제목1';

SELECT CONCAT('제목',' 1');

SELECT SUBSTRING(RAND() * 1000 FROM 1 FOR 2);

INSERT INTO articleset regDate = NOW(),updateDate = NOW(),title = CONCAT('제목',SUBSTRING(RAND() * 1000 FROM 1 FOR 2)),`body` = CONCAT('내용',SUBSTRING(RAND() * 1000 FROM 1 FOR 2));



UPDATE article
SET updateDate = NOW(),
    title = 'title1',
    `body` = 'body1'
WHERE id = 3;

UPDATE article
SET updateDate = NOW(),
    `body` = 'body1'
WHERE id = 1;

SELECT * FROM article;

SELECT COUNT(*)
FROM article
WHERE id = 5;

UPDATE article
SET updateDate = NOW(),
    title = 'title1',
    `body` = 'body1'
WHERE id = 5;