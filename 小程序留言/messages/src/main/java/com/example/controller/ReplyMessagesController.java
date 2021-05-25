package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.service.ReplyMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/replyMessage")
@RestController
@Api(description = "回复接口", tags = { "回复接口管理" })
public class ReplyMessagesController {

	@Autowired
	public ReplyMessagesService replyMessagesService;

	@ApiOperation(value = "回复", notes = "回复")
	@PostMapping("/insert")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "msgId", value = "留言id", dataTypeClass = String.class, required = true) ,
		@ApiImplicitParam(name = "content", value = "留言内容", dataTypeClass = String.class, required = true)
	})
	public Map insert(@RequestParam String msgId,@RequestParam String content) {
		Map map = new HashMap();
		try {
			if (replyMessagesService.insert(msgId,content) > 0) {
				map.put("code", "0000");
				map.put("msg", "回复成功");
			} else {
				map.put("code", "0001");
				map.put("msg", "回复失败");
			}
		} catch (Exception e) {
			map.put("code", "0001");
			map.put("msg", "回复失败");
		}
		return map;
	}

	@ApiOperation(value = "删除留言", notes = "删除留言")
	@DeleteMapping("/deleteMessages")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "留言id", dataTypeClass = String.class, required = true) ,
	})
	public Map deleteMessages(@RequestParam Integer id) {
		Map map = new HashMap();
		try {
			if (replyMessagesService.updateMessages(id) > 0) {
				map.put("code", "0000");
				map.put("msg", "删除评论成功");
			} else {
				map.put("code", "0001");
				map.put("msg", "删除评论失败");
			}
		} catch (Exception e) {
			map.put("code", "0001");
			map.put("msg", "删除评论失败");
		}
		return map;
	}
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "回复id", dataTypeClass = String.class, required = true) ,
	})
	@ApiOperation(value = "删除回复", notes = "删除回复")
	@DeleteMapping("/deleteReply")
	public Map deleteReply(@RequestParam Integer id) {
		Map map = new HashMap();
		try {
			if (replyMessagesService.updateReply(id) > 0) {
				map.put("code", "0000");
				map.put("msg", "删除回复成功");
			} else {
				map.put("code", "0001");
				map.put("msg", "删除回复失败");
			}
		} catch (Exception e) {
			map.put("code", "0001");
			map.put("msg", "删除回复失败");
		}
		return map;
	}

	@ApiOperation(value = "查询所有留言和回复", notes = "查询所有留言和回复")
	@GetMapping("/select")
	public Map select() {
		Map map = new HashMap();
		try {
			List<Map> selectAll = replyMessagesService.selectAll();
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
