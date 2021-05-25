package com.yxyz.rest;

/**
 * @description 错误信息
 *
 */
public class CodeMsg {

	private int code;
	private String msg;

	public static final CodeMsg SUCCESS = new CodeMsg(0, "操作成功");

	public static final CodeMsg REGIST_SUCCESS = new CodeMsg(0, "注册成功，请到邮箱激活用户");

	public static final CodeMsg ACTIVE_SUCCESS = new CodeMsg(0, "用户激活成功");

	public static final CodeMsg USEREXISTS = new CodeMsg(500101,"用户已经存在");

	public static final CodeMsg USENOTREXISTS = new CodeMsg(500102,"账号或密码错误");

	public static final CodeMsg SERVER_ERROR = new CodeMsg(500100,"服务端异常");

	//临期管理模块异常
	public CodeMsg(int code,String msg)
	{
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}


}
