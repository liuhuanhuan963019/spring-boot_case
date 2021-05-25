package com.example.move_login.login.mapper;

import com.example.move_login.login.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from tb_user where user_name = #{userName}")
    User getUserByName(String userName);

}
