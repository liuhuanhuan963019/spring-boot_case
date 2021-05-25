package com.lhh.service;

import com.lhh.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public String getPassword(String username) {
        return userMapper.getPassword(username);
    }

    public String getRole(String username) {
        return userMapper.getRole(username);
    }

    public int register(String username, String password, String role) {
        return userMapper.register(username,password,role);
    }

    public List<String>  getPermissionList(String role) {
        return userMapper.getPermissionList(role);
    }
}
