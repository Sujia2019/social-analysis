package com.psx.social.service;

import com.psx.social.dao.QuestionMapper;
import com.psx.social.dao.UserMoreMapper;
import com.psx.social.entity.*;
import com.psx.social.util.Constants;
import com.psx.social.util.ReturnT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMoreMapper userMoreMapper;

    @Override
    public List<Question> showQuestion() {
        return questionMapper.questionList();
    }

    @Override
    public List<Question> showQuestion(String account) {
        UserMore userMore = userMoreMapper.findByAccount(account);
        UserQuestion userQuestion = questionMapper.findUserQuestion(account);
        // 如果已经完成问卷
        if (userMore.isIsFinishedQ()) {
            QuestionPage questionPage = questionMapper.getQuestionPageById(userQuestion.getQuestionId());
            return getQuestions(questionPage);
        }
        // 随机获取一个,并将对应信息存库
        List<QuestionPage> pages = questionMapper.questionPages();
        Random r = new Random();
        int max = pages.size();
        int index = r.nextInt(max);
        Integer qId = pages.get(index).getId();
        // 如果是首次进入
        if (null == userQuestion) {
            userQuestion = new UserQuestion();
            userQuestion.setQuestionId(qId);
            userQuestion.setUser_account(account);
            questionMapper.insertUserQuestion(userQuestion);
        }
        // 更新
        userQuestion.setQuestionId(qId);
        questionMapper.updateUserQuestion(userQuestion);
        QuestionPage questionPage = questionMapper.getQuestionPageById(userQuestion.getQuestionId());
        return getQuestions(questionPage);
    }

    @Override
    public List<Question> getQuestionsById(Integer qId) {
        QuestionPage questionPage = questionMapper.getQuestionPageById(qId);
        if (questionPage != null) {
            return getQuestions(questionPage);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public int findAllFinished() {
        return userMoreMapper.findAllFinished();
    }

    @Override
    public double percent(int a, int b) {
        return Math.floor(((double) a / (double) b)*100);
    }

    @Override
    public AnalyzingData analyzingRes() {
        AnalyzingData data = new AnalyzingData();

        int finishedCount=findAllFinished();
        int allCount=userMoreMapper.findAllCount();

        data.setAllCount(allCount);
        data.setFinishedCount(finishedCount);

        data.setPercent(percent(finishedCount,allCount));
        List<Integer> list = userMoreMapper.findScore();
        int countA=0;
        int countB=0;
        int countC=0;
        int countD=0;
        for (Integer integer : list) {
            if (integer >= 48) {
                countA++;
            } else if (integer >= 37) {
                countB++;
            } else if (integer >= 27) {
                countC++;
            } else {
                countD++;
            }
        }
        data.setCountA(countA);
        data.setCountB(countB);
        data.setCountC(countC);
        data.setCountD(countD);
        data.setPercentA(percent(countA, finishedCount));
        data.setPercentB(percent(countB, finishedCount));
        data.setPercentC(percent(countC, finishedCount));
        data.setPercentD(percent(countD, finishedCount));
        return data;
    }

    @Override
    public ReturnT<?> addNewQuestion(Question question) {
        questionMapper.addQuestion(question);
        return new ReturnT<>(Constants.SUCCESS, "添加成功");
    }

    @Override
    public void delete(QuestionPage questionPage) {
        questionMapper.update(questionPage);
    }

    @Override
    public List<QuestionPage> getAvailableQuestions() {
        return questionMapper.questionPages();
    }

    @Override
    public ReturnT<?> addQuestionPage(QuestionPage questionPage) {
        if (questionMapper.getQuestionPageByName(questionPage.getPageName()) == null) {
            if (questionMapper.getQuestionPageByList(questionPage.getQuestionList()) == null)
                return new ReturnT<>(Constants.SUCCESS, "更新成功", questionMapper.insert(questionPage));
            return new ReturnT<>(Constants.FAIL, "更新失败，已有此序列问卷", questionMapper.insert(questionPage));
        }
        return new ReturnT<>(Constants.FAIL, "更新失败，名字重复", questionMapper.insert(questionPage));
    }

    private List<Question> getQuestions(QuestionPage questionPage) {
        String qs = questionPage.getQuestionList();
        // 去头去尾
        qs = qs.substring(1, qs.length() - 1);
        String[] subs = qs.split(",");
        List<Integer> qIds = new ArrayList<>();
        for (String s : subs) {
            qIds.add(Integer.parseInt(s));
        }
        List<Question> questionList = new ArrayList<>();
        for (Integer qId : qIds) {
            Question question = questionMapper.getQuestion(qId);
            questionList.add(question);
        }
        return questionList;
    }
}
