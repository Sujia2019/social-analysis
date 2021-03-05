package com.psx.social.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.psx.social.entity.*;
import com.psx.social.service.QuestionService;
import com.psx.social.service.UserService;
import com.psx.social.util.Constants;
import com.psx.social.util.PageUtil;
import com.psx.social.util.ReturnT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Api("答题相关")
@RestController
@RequestMapping("/question")
public class QuestionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionController.class);
    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;

    // 加载题目
    @ApiOperation("加载题目")
    @RequestMapping(value = "show", method = RequestMethod.GET)
    @ResponseBody
    public ReturnT<?> showQuestions(@RequestParam int curr, int pageSize, HttpServletRequest request) {
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("user");
        if (userDTO != null) {
            PageHelper.startPage(curr, pageSize);
            String account = userDTO.getUser_account();
            List<Question> questions = questionService.showQuestion(account);
            LOGGER.info("加载题目：{}", questions.toString());
            return PageUtil.getPageResult(new PageInfo<>(questions));
        }
        return new ReturnT<>(Constants.FAIL, "用户异常");


    }

    // 题目完成后保存答案
    @ApiOperation("题目完成后保存答案")
    @RequestMapping(value = "sendAnswers", method = RequestMethod.POST)
    @ResponseBody
    public ReturnT<?> Answers(@RequestBody int score, HttpServletRequest request) {
        UserDTO userDTO = ((UserDTO) request.getSession().getAttribute("user"));
        if (userDTO != null) {
            String account = userDTO.getUserInfo().getUser_account();
            HttpSession session = request.getSession();
            UserMore userMore = userService.addScores(account, score);
            userDTO.setUserMore(userMore);
            session.setAttribute("User", userDTO);
            LOGGER.info("保存用户【{}】的更多信息【{}】至session中...", account, userMore.toString());
            return new ReturnT<>(Constants.SUCCESS, "保存答案成功", userMore);
        }
        return new ReturnT<>(Constants.FAIL, "保存答案失败，请重试");
    }

    // 获取答题测评结果
    @ApiOperation("获取答题测评结果")
    @RequestMapping(value = "getResult", method = RequestMethod.GET)
    @ResponseBody
    public ReturnT<?> getResult(@RequestParam String account) {
        return new ReturnT<>(Constants.SUCCESS, Constants.SUCCESS_MSG, userService.returnResult(account));
    }

    // 检测是否完成题目
    @ApiOperation("检测是否完成题目")
    @RequestMapping(value = "isFinished", method = RequestMethod.GET)
    @ResponseBody
    public ReturnT<?> isFinished(@RequestParam String account) {
        ReturnT<?> returnT;
        boolean isOk = userService.isFinished(account);
        if (isOk) {
            returnT = new ReturnT<>(Constants.SUCCESS, "恭喜您已完成测评题目", true);
        } else {
            returnT = new ReturnT<>(Constants.SUCCESS, "您还未完成测评题目，正在跳转页面", false);
        }
        return returnT;
    }


}
