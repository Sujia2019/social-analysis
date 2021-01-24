package com.psx.social.dao;


import com.psx.social.entity.QuestionPage;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionMapper {

    @Select("select * from QuestionPage")
    List<QuestionPage> questionList();

    int insert();

    int update();
}
