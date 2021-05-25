package com.lhh.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserMapper {
    /**
     * 获得密码
     */
    String getPassword(String username);

    /**
     * 获得角色权限
     */
    String getRole(String username);


    int register(@Param("username") String username, @Param("password") String password, @Param("role") String role);

    /**
     * 获得用户的权限
     */
    List<String>  getPermissionList(String role);
}
