package com.psx.social.service;

import com.psx.social.dao.QuestionMapper;
import com.psx.social.dao.UserMoreMapper;
import com.psx.social.entity.AnalyzingData;
import com.psx.social.entity.Question;
import com.psx.social.entity.QuestionPage;
import com.psx.social.util.LoadTxt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Question> showQuestion() {
        QuestionPage page = questionMapper.questionList().get(0);
        return LoadTxt.ReadQuestions(page.getPageUrl());
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
        data.setPercentA(percent(countA,finishedCount));
        data.setPercentB(percent(countB,finishedCount));
        data.setPercentC(percent(countC,finishedCount));
        data.setPercentD(percent(countD,finishedCount));
        return data;
    }
}
