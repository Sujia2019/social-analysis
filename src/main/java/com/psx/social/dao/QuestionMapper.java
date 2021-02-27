package com.psx.social.dao;


import com.psx.social.entity.QuestionPage;
import com.psx.social.entity.UserQuestion;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionMapper {

    @Select("select * from QuestionPage")
    List<QuestionPage> questionList();

    @Insert("insert into QuestionPage(pageUrl,pageName) values (#{pageUrl},#{pageName})")
    int insert(QuestionPage questionPage);

    int update();

    @Insert("insert into UserQuestion(user_account,question_url,res_url) " +
            "values(#{user_account},#{question_url},#{res_url})")
    int insertUserQuestion(UserQuestion userQuestion);

    @Select("select * from UserQuestion where user_account=#{account}")
    UserQuestion findUserQuestion(String account);

    @Select("select count(*) from UserQuestion")
    int countPage();
}
