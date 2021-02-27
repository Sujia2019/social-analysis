package com.psx.social.service;

import com.psx.social.dao.QuestionMapper;
import com.psx.social.dao.UserMoreMapper;
import com.psx.social.entity.AnalyzingData;
import com.psx.social.entity.Question;
import com.psx.social.entity.QuestionPage;
import com.psx.social.entity.UserQuestion;
import com.psx.social.util.Constants;
import com.psx.social.util.LoadTxt;
import com.psx.social.util.ReturnT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Transactional
@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMoreMapper userMoreMapper;

    @Override
    public List<Question> showQuestion(int curr, int pageSize) {
        return null;
    }

    @Override
    public List<Question> showQuestion(String account) {
        // 随机获取一个,并将对应信息存库
        List<QuestionPage> pages = questionMapper.questionList();
        Random r = new Random();
        int max = pages.size();
        int index = r.nextInt(max);
        String url = pages.get(index).getPageUrl();
        UserQuestion userQuestion = new UserQuestion();
        userQuestion.setQuestion_url(url);
        userQuestion.setUser_account(account);
        userQuestion.setRes_url(url + "_Res");
        questionMapper.insertUserQuestion(userQuestion);
        return LoadTxt.ReadQuestions(url);
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
    public ReturnT<?> addNewQuestions(List<Question> questions, List<String> res) {
        try {
            QuestionPage questionPage = new QuestionPage();
            String tail = questionMapper.countPage() + 1 + "";
            String pageName = "Research_" + tail;
            String url = "questions/" + pageName;
            questionPage.setPageName(pageName);
            questionPage.setPageUrl(url);
            questionMapper.insert(questionPage);
            LoadTxt.writeQuestions(url, questions);
            LoadTxt.writeRes(url + "_Res", res);
        } catch (Exception e) {
            return new ReturnT<>(Constants.FAIL, e.getMessage());
        }
        return new ReturnT<>(Constants.SUCCESS, "添加成功");
    }
}
