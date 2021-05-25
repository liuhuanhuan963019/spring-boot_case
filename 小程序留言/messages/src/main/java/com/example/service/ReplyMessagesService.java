package com.example.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.example.mapper.ReplyMessagesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @version V1.0
 * @Package com.example.demo.service
 * @date 2020/05/13 14:33
 */
@Service
public class ReplyMessagesService {

    @Autowired
    public ReplyMessagesMapper replyMessagesMapper;


    public int insert(String msgId,String content) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return replyMessagesMapper.insert(msgId,content, sdf.format(new Date()));
     }

     public int updateReply(Integer id) {
         return replyMessagesMapper.updateReply(id);
     }

     public List<Map> selectAll() {
         return replyMessagesMapper.selectAll();
     }

	public int updateMessages(Integer id) {
		// TODO Auto-generated method stub
		return replyMessagesMapper.updateMessages(id);
	}
}
