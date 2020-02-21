package com.kailo.checksql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select(" select u.name as user_name,\n" +
            "       u.age as user_age,\n" +
            "       s.name as student_name,\n" +
            "       l.grade as lesson_grade,\n" +
            "       m.channel as market_channel\n" +
            " from users u,\n" +
            "     students s,\n" +
            "     student_lesson_infos l,\n" +
            "     student_market_infos m\n" +
            " where s.user_id = u.id\n" +
            "  and l.student_id = s.id\n" +
            "  and m.student_id = s.id ")
    public List<UserInfo> selectUserInfo();
}
