package com.yxyz.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yxyz.vo.User;

/**
* Copyright @ 2020 Zonlyn. All rights reserved.

* @Description: 该类的功能描述
*
* @version: v1.0.0

*/
@Repository
public interface UserDao
{
	void insert(User vo);

	void update(String code);

	User findByUserName(String userName);

	User findByNameAndPwd(Map<String,String> map);
}
