package com.psx.social.service;

import com.psx.social.entity.AnalyzingData;
import com.psx.social.entity.Question;
import com.psx.social.util.ReturnT;

import java.util.List;

public interface QuestionService {
    List<Question> showQuestion(int curr, int pageSize);

    List<Question> showQuestion(String account);

    // 所有已完成答题的数目
    int findAllFinished();

    // 百分比
    double percent(int a, int b);

    // 答题结果分析
    AnalyzingData analyzingRes();

    // 添加调查问卷
    ReturnT<?> addNewQuestions(List<Question> questions, List<String> res);
}
