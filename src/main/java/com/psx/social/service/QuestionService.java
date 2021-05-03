package com.psx.social.service;

import com.psx.social.entity.AnalyzingData;
import com.psx.social.entity.Question;
import com.psx.social.entity.QuestionPage;
import com.psx.social.util.ReturnT;

import java.util.List;

public interface QuestionService {
    List<Question> showQuestion();

    List<Question> showQuestion(String account);

    List<Question> getQuestionsById(Integer qId);

    // 所有已完成答题的数目
    int findAllFinished();

    // 百分比
    double percent(int a, int b);

    // 答题结果分析
    AnalyzingData analyzingRes();

    // 添加题目
    ReturnT<?> addNewQuestion(Question question);

    void delete(QuestionPage questionPage);

    List<QuestionPage> getAvailableQuestions();

    // 添加问卷
    ReturnT<?> addQuestionPage(QuestionPage questionPage);


    void doAnalyze(String msg);


}
