package com.psx.social.dao;


import com.psx.social.entity.QuestionPage;
import com.psx.social.entity.UserQuestion;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionMapper {

    @Select("select * from QuestionPage where available=${1}")
    List<QuestionPage> questionList();

    @Insert("insert into QuestionPage(pageUrl,pageName,available) values (#{pageUrl},#{pageName},${1})")
    int insert(QuestionPage questionPage);

    @Update("update psx.QuestionPage set available = #{available}")
    int update(QuestionPage questionPage);

    @Insert("insert into UserQuestion(user_account,question_url,res_url) " +
            "values(#{user_account},#{question_url},#{res_url})")
    int insertUserQuestion(UserQuestion userQuestion);

    @Update("update psx.UserQuestion set question_url=#{question_url} where user_account=#{user_account}")
    int updateUserQuestion(UserQuestion userQuestion);

    @Select("select * from UserQuestion where user_account=#{account}")
    UserQuestion findUserQuestion(String account);

    @Select("select count(*) from QuestionPage")
    int countPage();
}
