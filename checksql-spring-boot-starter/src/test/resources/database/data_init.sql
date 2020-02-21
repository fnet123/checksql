DELETE FROM users;

INSERT INTO users (id, name, age) VALUES
(1, 'Jone', 18),
(2, 'Jack', 20),
(3, 'Tom', 28),
(4, 'Sandy', 21),
(5, 'Billie', 24);

DELETE FROM students;

INSERT INTO students (id, name, user_id) VALUES
(1, 'Jone Son', 1),
(2, 'Jack Son', 2),
(3, 'Tom Son', 3),
(4, 'Sandy Son', 4),
(5, 'Billie Son', 5);

DELETE FROM student_lesson_infos;

INSERT INTO student_lesson_infos (id, grade, student_id) VALUES
(1, '一年级', 1),
(2, '二年级', 2),
(3, '一年级', 3),
(4, '二年级', 4),
(5, '一年级', 5);

DELETE FROM student_market_infos;

INSERT INTO student_market_infos (id, channel, student_id) VALUES
(1, 'aliyun', 1),
(2, 'baidu', 2),
(3, 'taobao', 3),
(4, 'jingdong', 4),
(5, 'baidu', 5);