package com.example.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ReplyMessagesMapper {


	@Insert("insert into replyMessages (msgId,replyContent,createTime) values (#{param1},#{param2},#{param3})")
    int insert(String msgId,String replyContent,String createTime);

    @Select("select a.*,b.id as replyId,b.replyContent,b.createTime as replyCreateTime from messages a left join replyMessages b on a.id = b.msgId and b.status ='0' \r\n" +
    		"where a.status = '0' order by a.createTime desc")
    List<Map> selectAll();

    @Update("update replyMessages set status = '1' where id = #{id}")
    int updateReply(Integer id);

    @Update("update messages set status = '1' where id = #{id}")
    int updateMessages(Integer id);
}
