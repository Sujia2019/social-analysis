package com.psx.social.dao;

import com.psx.social.entity.BoardMsg;
import com.psx.social.entity.Tips;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardMapper {
    @Select("select board_text,from_name,from_account,date_time from BoardMsg" +
            " where user_account=#{account} order by date_time DESC")
    List<BoardMsg> showBoardMsg(String account);

    @Insert("insert into BoardMsg(user_account,from_name,from_account,board_text,date_time)" +
            "values(#{user_account},#{from_name},#{from_account},#{board_text},now())")
    boolean insertBoardMsg(BoardMsg msg);

    @Select("select count(*) from BoardMsg where user_account=#{account}")
    int countBoardMsg(String account);

    @Select("select msgCount from UserMore where user_account=#{account}")
    int historyMsgCount(String account);
    @Update("update UserMore set msgCount=#{count} where user_account=#{account}")
    int setHistoryMsgCount(Tips tips);
}
