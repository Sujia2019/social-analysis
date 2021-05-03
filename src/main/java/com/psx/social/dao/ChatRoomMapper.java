package com.psx.social.dao;

import com.psx.social.entity.ChatRoom;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomMapper {

    @Insert("insert into ChatRoom(user_account,msg_count,create_time,last_modify,activity_index,positive,negative,positive_prob,negative_prob,neutral_prob)" +
            "values(#{user_account},#{msg_count},now(),now(),#{activity_index},#{positive},#{negative},#{positive_prob},#{negative_prob},#{neutral_prob})")
    void insertChatRoom(ChatRoom chat);

    @Select("select * from ChatRoom where user_account=#{account}")
    ChatRoom getChatInfo(String account);

    @Select("select count(*) from ChatRoom where user_account=#{account}")
    int getChatNumber(String account);

    @Update("update ChatRoom set msg_count=#{msg_count}," +
            "last_modify=now(),activity_index=#{activity_index},positive=#{positive},negative=#{negative},positive_prob=#{positive_prob},negative_prob=#{negative_prob},neutral_prob=#{neutral_prob} " +
            "where user_account=#{user_account}")
    int update(ChatRoom chat);

    @Select("select u.r from (" +
            "select user_account,(@ranknum:=@ranknum+1) as r from ChatRoom,(select (@ranknum :=0)) b order by activity_index desc)" +
            " u where u.user_account=#{user_account}")
    int getRank(String user_account);


    @Select("select count(*) from ChatRoom where activity_index >= 20000")
    int countActivityA();

    @Select("select count(*) from ChatRoom where activity_index >= 12000 and activity_index < 20000")
    int countActivityB();

    @Select("select count(*) from ChatRoom where activity_index >= 6000 and activity_index < 12000")
    int countActivityC();

    @Select("select count(*) from ChatRoom where activity_index >= 2000 and activity_index < 6000")
    int countActivityD();

    @Select("select count(*) from ChatRoom where activity_index >= 0 and activity_index < 2000")
    int countActivityE();
}
