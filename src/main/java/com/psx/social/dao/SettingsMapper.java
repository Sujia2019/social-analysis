package com.psx.social.dao;

import com.psx.social.entity.Settings;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsMapper {

    @Insert("insert into Settings(user_account,receive_friend,receive_board,show_board,send_notice)values(" +
            "#{user_account},#{receive_friend},#{receive_board},#{show_board},#{send_notice})")
    int insert(Settings settings);
    @Update("update Settings set receive_friend=#{receive_friend},receive_board=#{receive_board},show_board=#{show_board},send_notice=#{send_notice}" +
            "where user_account=#{user_account}")
    int update(Settings settings);
    @Delete("delete from psx_db.Settings where user_account=#{account}")
    int delete(String user_account);

    @Select("select * from Settings where user_account=#{account}")
    Settings findByAccount(String account);

}