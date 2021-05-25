package com.example.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MessagesMapper {

    @Update("update messages set status = '1' where id = #{id}")
    int delete(Integer id);

    @Insert("insert into messages (content,createTime) values (#{content},#{time})")
    int insert(String content,String time);

    /*@Select("select * from messages where 1=1")
    List<Map> selectAll();*/

    @Select("select a.*,b.id as replyId,b.replyContent,b.createTime as replyCreateTime from messages a left join replyMessages b on a.id = b.msgId and b.status ='0' \r\n" +
    		"where a.status = '0' order by a.createTime desc")
    List<Map> selectAll();
}
