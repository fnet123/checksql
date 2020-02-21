DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id   BIGINT(20)  NOT NULL COMMENT 'id',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT 'name',
    age  INT(11)     NULL DEFAULT NULL COMMENT 'age',
    PRIMARY KEY (id)
);

CREATE TABLE students
(
    id      BIGINT(20)  NOT NULL COMMENT 'id',
    user_id BIGINT(20)  NULL DEFAULT NULL COMMENT 'user_id',
    name    VARCHAR(30) NULL DEFAULT NULL COMMENT 'name',
    PRIMARY KEY (id)
);

CREATE TABLE student_lesson_infos
(
    id         BIGINT(20)  NOT NULL COMMENT 'id',
    student_id BIGINT(20)  NULL DEFAULT NULL COMMENT 'student_id',
    grade      VARCHAR(30) NULL DEFAULT NULL COMMENT 'grade',
    PRIMARY KEY (id)
);

CREATE TABLE student_market_infos
(
    id         BIGINT(20)  NOT NULL COMMENT 'id',
    student_id BIGINT(20)  NULL DEFAULT NULL COMMENT 'student_id',
    channel    VARCHAR(30) NULL DEFAULT NULL COMMENT 'channel',
    PRIMARY KEY (id)
);