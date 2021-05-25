package com.example.move_login.login.controller;

import com.example.move_login.login.entity.User;
import com.example.move_login.login.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(description = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {
        Map map = userService.login(user.getUsername(), user.getPassword());
        return map;
    }
}
