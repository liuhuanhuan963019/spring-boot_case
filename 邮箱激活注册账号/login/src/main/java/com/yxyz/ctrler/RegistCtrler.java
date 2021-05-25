package com.yxyz.ctrler;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.yxyz.rest.CodeMsg;
import com.yxyz.rest.Result;
import com.yxyz.service.UserRegistService;
import com.yxyz.vo.User;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
* Copyright @ 2020 Zonlyn. All rights reserved.

* @Description: 该类的功能描述
*
* @version: v1.0.0
*/
@RestController
@CrossOrigin
public class RegistCtrler
{
	@Autowired
	private UserRegistService userRegistService;

	@ApiOperation(value="用户注册")
	@PostMapping("/regist")
	public Object regist(@Valid User vo)
	{
		try
		{
			return userRegistService.regist(vo);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		 return Result.error(CodeMsg.SERVER_ERROR);
	}

	@Transactional
	@ApiOperation(value="用户激活")
	@GetMapping("/active")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "激活码",dataTypeClass = String.class,required = true)
	})
	public Object active(@RequestParam("code") String code)
	{
		try
		{
			return userRegistService.update(code);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		 return Result.error(CodeMsg.SERVER_ERROR);
	}
	@ApiOperation(value="用户登录")
	@PostMapping("/active")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "username", value = "用户名",dataTypeClass = String.class,required = true),
		@ApiImplicitParam(name = "pwd", value = "密码",dataTypeClass = String.class,required = true)
	})
	public Object login(@RequestParam("username") String username,@RequestParam("pwd") String pwd)
	{

		try
		{
			return userRegistService.login(username, pwd);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return Result.error(CodeMsg.SERVER_ERROR);
	}

}
