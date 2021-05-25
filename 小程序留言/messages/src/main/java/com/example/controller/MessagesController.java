package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/message")
@RestController
@Api(description = "留言接口", tags = { "留言接口管理" })
public class MessagesController {

	@Autowired
	public MessagesService messagesService;

	@ApiOperation(value = "留言", notes = "留言")
	@PostMapping("/insert")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "content", value = "留言内容", dataTypeClass = String.class, required = true)
	})
	public Map insert(@RequestParam String content) {
		Map map = new HashMap();
		try {
			if (messagesService.insert(content) > 0) {
				map.put("code", "0000");
				map.put("msg", "留言成功");
			} else {
				map.put("code", "0001");
				map.put("msg", "留言失败");
			}
		} catch (Exception e) {
			map.put("code", "0001");
			map.put("msg", "留言失败");
		}
		return map;
	}
	@ApiOperation(value = "查询所有留言和回复", notes = "查询所有留言和回复")
	@GetMapping("/select")
	public Map select() {
		Map map = new HashMap();
		try {
			List<Map> selectAll = messagesService.selectAll();
			map.put("list", selectAll);
			map.put("code", "0000");
			map.put("msg", "查询成功");
		} catch (Exception e) {
			map.put("code", "0001");
			map.put("msg", "查询失败");
		}
		return map;
	}
}
