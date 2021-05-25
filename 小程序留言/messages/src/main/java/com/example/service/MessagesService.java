package com.example.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.example.mapper.MessagesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @version V1.0
 * @Package com.example.demo.service
 * @date 2020/05/13 14:33
 */
@Service
public class MessagesService {

    @Autowired
    public MessagesMapper messagesMapper;


    public int insert(String content) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return messagesMapper.insert(content, sdf.format(new Date()));
     }

     public List<Map> selectAll() {
         return messagesMapper.selectAll();
     }
}
