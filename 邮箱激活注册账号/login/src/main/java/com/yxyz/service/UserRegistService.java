package com.yxyz.service;

import com.yxyz.vo.User;

/**
* Copyright @ 2020 Zonlyn. All rights reserved.

* @Description: 该类的功能描述
*
* @version: v1.0.0
*/
public interface UserRegistService
{
	Object regist(User vo);

	Object update(String code);

	Object login(String username,String pwd);
}
