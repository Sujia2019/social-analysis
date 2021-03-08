package com.psx.social.dao;

import com.psx.social.entity.UserMore;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMoreMapper {
    @Insert("insert into UserMore(user_account,isFinishedQ,scores,msgCount,requestCount)" +
            " values (#{user_account},#{isFinishedQ},#{scores},#{msgCount},#{requestCount})")
    int insert(UserMore userMore);
    @Delete("delete from psx_db.UserMore where user_account=#{account}")
    int delete(String account);
    @Update("update psx_db.UserMore SET " +
            "isFinishedQ=#{isFinishedQ},scores=#{scores},msgCount=#{msgCount},requestCount=#{requestCount}" +
            " where user_account=#{user_account}")
    int update(UserMore userMore);
    @Select("select * from UserMore where user_account=#{account}")
    UserMore findByAccount(String account);

    @Update("update UserMore set scores =#{score} where user_account=#{account}")
    int setScore(String account,int score);

    @Select("select isFinishedQ from UserMore where user_account=#{account}")
    boolean isFinished(String account);

    @Select("select scores from UserMore where user_account=#{account}")
    int findScoreByAccount(String account);

    @Select("select count(*) from UserMore where isFinishedQ=1")
    int findAllFinished();
    @Select("select count(*) from UserMore")
    int findAllCount();
    @Select("select scores from UserMore where isFinishedQ=1")
    List<Integer> findScore();

    @Update("update  psx_db.UserMore SET isFinishedQ=${1} where user_account=#{account}")
    int updateFinished(String account);
}
