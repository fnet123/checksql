DROP TABLE IF EXISTS sys_config;

CREATE TABLE sys_config
(
    variable VARCHAR(30) NOT NULL COMMENT 'variable',
    value VARCHAR(30) NULL DEFAULT NULL COMMENT 'value',
    set_time DATETIME NULL DEFAULT NULL COMMENT 'set_time',
    set_by VARCHAR(30) NULL DEFAULT NULL COMMENT 'set_time',
    PRIMARY KEY (variable)
);