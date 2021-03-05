package com.psx.social.dao;


import com.psx.social.entity.Question;
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
    List<QuestionPage> questionPages();

    @Select("select * from Question")
    List<Question> questionList();

    @Insert("insert into QuestionPage(questionList,pageName,available) values (#{questionList},#{pageName},${1})")
    int insert(QuestionPage questionPage);

    @Update("update QuestionPage set available = #{available}")
    int update(QuestionPage questionPage);

    @Insert("insert into UserQuestion(user_account,questionId) " +
            " values (#{user_account},#{questionId})")
    int insertUserQuestion(UserQuestion userQuestion);

    @Update("update UserQuestion set questionId=#{questionId} where user_account=#{user_account}")
    int updateUserQuestion(UserQuestion userQuestion);

    @Select("select * from UserQuestion where user_account=#{account}")
    UserQuestion findUserQuestion(String account);

    @Select("select count(*) from QuestionPage")
    int countPage();

    @Insert("insert into Question(title,answer1,answer2,answer3) values(#{title},#{answer1},#{answer2},#{answer3})")
    int addQuestion(Question question);

    @Select("select * from Question where id = #{id}")
    Question getQuestion(Integer id);

    @Select("select * from QuestionPage where id = #{id}")
    QuestionPage getQuestionPageById(Integer id);

    @Select("select * from QuestionPage where pageName = #{pageName}")
    QuestionPage getQuestionPageByName(String pageName);

    @Select("select * from QuestionPage where questionList = #{list}")
    QuestionPage getQuestionPageByList(String list);
}
