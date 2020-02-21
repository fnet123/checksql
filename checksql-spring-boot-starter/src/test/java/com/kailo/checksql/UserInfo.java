package com.kailo.checksql;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {

    private String userName;
    private Integer userAge;
    private String studentName;
    private String lessonGrade;
    private String marketChannel;
}
