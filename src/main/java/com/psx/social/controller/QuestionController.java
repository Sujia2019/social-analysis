package com.psx.social.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.psx.social.entity.Question;
import com.psx.social.entity.ReturnAnswer;
import com.psx.social.entity.UserInfo;
import com.psx.social.entity.UserMore;
import com.psx.social.service.QuestionService;
import com.psx.social.service.UserService;
import com.psx.social.util.Constants;
import com.psx.social.util.PageUtil;
import com.psx.social.util.ReturnT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionController.class);
    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;

    // 加载题目
    @RequestMapping("show")
    @ResponseBody
    public ReturnT<?> showQuestions(@RequestParam int curr, int pageSize){
        PageHelper.startPage(curr, pageSize);
        List<Question> questions = questionService.showQuestion();
        LOGGER.info("加载题目：{}",questions.toString());
        return PageUtil.getPageResult(new PageInfo<>(questions));

    }

    // 题目完成后保存答案
    @RequestMapping("sendAnswers")
    @ResponseBody
    public ReturnT<?> Answers(@RequestBody List<ReturnAnswer> answersList, HttpServletRequest request) {
        String account = ((UserInfo)request.getSession().getAttribute("UserInfo")).getUser_account();
        HttpSession session = request.getSession();
        UserMore userMore = userService.addScores(account,answersList);
        session.setAttribute("UserMore",userMore);
        LOGGER.info("保存用户【{}】的更多信息【{}】至session中...",account,userMore.toString());
        return new ReturnT<>(Constants.SUCCESS,"保存答案成功",userMore);
    }

    // 获取答题测评结果
    @RequestMapping("getResult")
    @ResponseBody
    public ReturnT<?> getResult(@RequestParam String account){
        return new ReturnT<>(Constants.SUCCESS,Constants.SUCCESS_MSG,userService.returnResult(account));
    }

    // 检测是否完成题目
    @RequestMapping("isFinished")
    @ResponseBody
    public ReturnT<?> isFinished(@RequestParam String account){
        ReturnT<?> returnT;
        boolean isOk = userService.isFinished(account);
        if(isOk){
            returnT = new ReturnT<>(Constants.SUCCESS,"恭喜您已完成测评题目",true);
        }else{
            returnT = new ReturnT<>(Constants.SUCCESS,"您还未完成测评题目，正在跳转页面",false);
        }
        return returnT;
    }
}
