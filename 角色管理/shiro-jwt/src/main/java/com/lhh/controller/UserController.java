package com.lhh.controller;


import com.lhh.entity.Msg;
import com.lhh.service.UserService;
import com.lhh.util.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@Api(value = "测试Controller")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册", notes = "填写用户名，密码，选择权限(boss,leader,admin,user)")
    @PostMapping("/register")
    public Msg register(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("role") String role) {

        int register = userService.register(username, password, role);
        if (register>0){
            return Msg.success().add("info","注册成功");
        }else {
         return Msg.fail().add("info","注册失败");
        }
    }


    @ApiOperation(value = "用户登录", notes = "登录--不进行拦截,所有角色用户都可以登录")
    @PostMapping("/login")
    public Msg login(@RequestParam("username") String username,
                     @RequestParam("password") String password) {
        String realPassword = userService.getPassword(username);
        if (realPassword == null) {
            return Msg.fail().add("info", "用户名错误");
        } else if (!realPassword.equals(password)) {
            return Msg.fail().add("info", "密码错误");
        } else {
            return Msg.success().add("token", JWTUtil.createToken(username));
        }
    }

    @ApiOperation(value = "测试无权限", notes = "无权限跳转的接口")
    @GetMapping(path = "/unauthorized/{message}")
    public Msg unauthorized(@PathVariable String message) {
        return Msg.fail().add("info", message);
    }



    @ApiOperation(value = "特定角色访问", notes = "拥有boss/leader角色的用户可以访问下面的页面")
    @GetMapping("/getRole")
    @RequiresRoles(logical = Logical.OR, value = {"admin", "boss"})
    public Msg getMessage() {
        return Msg.success().add("info", "访问成功！");
    }


    @ApiOperation(value = "boss角色", notes = "可以访问boss/leader/admin/user 页面")
    @PostMapping("/getBossMessage")
    @RequiresRoles(logical = Logical.OR, value = {"boss"})
    @RequiresPermissions(logical = Logical.OR,value = {"boss","leader","admin","user"})
    public Msg getBossMessage() {
        return Msg.success().add("info", "访问boss页面成功 ！");
    }

    @ApiOperation(value = "leader角色", notes = "可以访问leader/admin/user 页面")
    @PostMapping("/getLeaderMessage")
    @RequiresRoles(logical = Logical.OR, value = {"boss","leader"})
    @RequiresPermissions(logical = Logical.OR,value = {"boss","leader","admin","user"})
    public Msg getLeaderMessage() {
        return Msg.success().add("info", "访问leader页面成功 ！");
    }

    @ApiOperation(value = "admin角色", notes = "可以访问admin/user 页面")
    @PostMapping("/getAdminMessage")
    @RequiresRoles(logical = Logical.OR, value = {"boss","leader","admin"})
    @RequiresPermissions(logical = Logical.OR,value = {"boss","leader","admin","user"})
    public Msg getAdminMessage() {
        return Msg.success().add("info", "访问admin页面成功 ！");
    }


    @ApiOperation(value = "user角色", notes = "可以访问user页面")
    @PostMapping("/getUserMessage")
    @RequiresRoles(logical = Logical.OR, value = {"boss","leader","admin","user"})
    @RequiresPermissions(logical = Logical.OR,value = {"boss","leader","admin","user"})
    public Msg getUserMessage() {
        return Msg.success().add("info", "访问user成功 ！");
    }


}
