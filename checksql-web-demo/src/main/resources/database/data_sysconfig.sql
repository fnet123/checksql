DELETE FROM sys_config;

INSERT INTO sys_config (variable, value, set_time, set_by) VALUES
('a', 'Jone', now(), '1'),
('b', 'Jack', now(), '2'),
('c', 'Tom', now(), '3'),
('d', 'Sandy', now(), '4'),
('e', 'Billie', now(), '5');