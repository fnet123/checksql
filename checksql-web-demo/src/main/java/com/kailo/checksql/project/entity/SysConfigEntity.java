package com.kailo.checksql.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_config")
public class SysConfigEntity implements Serializable {
    @TableId(value = "variable", type = IdType.NONE)
    private String variable;

    private String value;
    private Date setTime;
    private String setBy;
}
