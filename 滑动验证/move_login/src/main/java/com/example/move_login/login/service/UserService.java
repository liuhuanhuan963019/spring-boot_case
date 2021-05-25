package com.example.move_login.login.service;


import com.example.move_login.login.entity.User;
import com.example.move_login.login.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    public UserMapper userMapper;

    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        User user = userMapper.getUserByName(username);
        if (user == null) {
            map.put("code", "0001");
            map.put("msg", "该账号不存在!");
            return map;
        }
        if (!password.equals(user.getPassword())) {
            map.put("code", "0002");
            map.put("msg", "密码错误!");
            return map;
        }
        map.put("code", "0000");
        map.put("msg", "登录成功!");
        map.put("data", user);
        return map;
    }
}
