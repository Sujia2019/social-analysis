package com.psx.social.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.psx.social.entity.*;
import com.psx.social.service.BoardService;
import com.psx.social.service.QuestionService;
import com.psx.social.service.UserService;
import com.psx.social.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("管理端")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    UserService userService;
    @Autowired
    QuestionService questionService;
    @Autowired
    BoardService boardService;

    @ApiOperation("查询所有学生")
    @RequestMapping(value = "findAll", method = RequestMethod.GET)//查询所有
    @ResponseBody
    public ReturnT<?> findAll(@RequestParam(required = false) int index,
                              @RequestParam(required = false) int perPage) {
        PageHelper.startPage(index, perPage);
        List<UserInfo> users = userService.findAll();
        return PageUtil.getPageResult(new PageInfo<>(users));
    }

    @ApiOperation("根据账号查询学生，模糊查询")
    @RequestMapping(value = "findUsersByAccount", method = RequestMethod.GET)
    @ResponseBody
    public ReturnT<?> findUsersByAccount(@RequestParam String account,
                                         @RequestParam(required = false) int index,
                                         @RequestParam(required = false) int perPage) {
        PageHelper.startPage(index, perPage);
        List<UserInfo> users = userService.findUsersByAccount(account);
        return PageUtil.getPageResult(new PageInfo<>(users));
    }

    @ApiOperation("根据年龄查询学生")
    @RequestMapping(value = "findUsersByAge", method = RequestMethod.GET)
    @ResponseBody
    public ReturnT<?> findUsersByAge(@RequestParam int age,
                                     @RequestParam(required = false) int index,
                                     @RequestParam(required = false) int perPage) {
        PageHelper.startPage(index, perPage);
        List<UserInfo> users = userService.findUsersByAge(age);
        return PageUtil.getPageResult(new PageInfo<>(users));
    }

    @ApiOperation("根据学校查询学生")
    @RequestMapping(value = "findUsersByCollage", method = RequestMethod.GET)
    @ResponseBody
    public ReturnT<?> findUsersByCollage(@RequestParam String collage,
                                         @RequestParam(required = false) int index,
                                         @RequestParam(required = false) int perPage) {
        PageHelper.startPage(index, perPage);
        List<UserInfo> users = userService.findUsersByCollage(collage);
        return PageUtil.getPageResult(new PageInfo<>(users));
    }

    @ApiOperation("根据专业查询学生")
    @RequestMapping(value = "findUsersByMajor", method = RequestMethod.GET)
    @ResponseBody
    public ReturnT<?> findUsersByMajor(@RequestParam String major,
                                       @RequestParam(required = false) int index,
                                       @RequestParam(required = false) int perPage) {
        PageHelper.startPage(index, perPage);
        List<UserInfo> users = userService.findUsersByMajor(major);
        return PageUtil.getPageResult(new PageInfo<>(users));
    }

    @ApiOperation("根据姓名查询学生，模糊查询")
    @RequestMapping(value = "findUsersByName", method = RequestMethod.GET)
    @ResponseBody
    public ReturnT<?> findUsersByName(@RequestParam String name,
                                      @RequestParam(required = false) int index,
                                      @RequestParam(required = false) int perPage) {
        PageHelper.startPage(index, perPage);
        List<UserInfo> users = userService.findUsersByName(name);
        return PageUtil.getPageResult(new PageInfo<>(users));
    }

    @ApiOperation("根据不同条件，模糊查询")
    @RequestMapping(value = "findUsersByLikeQuery", method = RequestMethod.POST)
    @ResponseBody
    public ReturnT<?> findUsersByLikeQuery(@RequestBody UserInfoDTO userInfoDTO) {
        PageHelper.startPage(userInfoDTO.getIndex(), userInfoDTO.getPerPage());
        List<UserInfo> users = userService.findUsersInfoByLikeQuery(userInfoDTO.getUserInfo());
        return PageUtil.getPageResult(new PageInfo<>(users));
    }

    @ApiOperation("根据账号删除")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ReturnT<?> delete(@RequestParam String account) {
        if (userService.delUser(account))
            return new ReturnT<>(Constants.SUCCESS, "删除成功");
        else {
            return new ReturnT<>(Constants.FAIL, "删除失败");
        }
    }

    @ApiOperation("答题完成情况")
    @RequestMapping(value = "finishedPercent", method = RequestMethod.GET)
    @ResponseBody
    public ReturnT<?> finishedPercent() {
        AnalyzingData data = questionService.analyzingRes();
        LOGGER.info("答题情况：{}", data.toString());
        return new ReturnT<>(Constants.SUCCESS, "查询答题完成情况成功", data);
    }

    @ApiOperation("获得日志名称")
    @RequestMapping(value = "getChatLogsName", method = RequestMethod.GET)
    @ResponseBody
    public ReturnT<?> getChatLogsName() {
        return new ReturnT<>(Constants.SUCCESS, ChatListener.getLogsName());
    }

    @ApiOperation("根据名称获得日志")
    @RequestMapping(value = "getChatLog", method = RequestMethod.GET)
    @ResponseBody
    public ReturnT<?> getChatLog(@RequestParam String fileName) {
        return new ReturnT<>(Constants.SUCCESS, ChatListener.getLog(fileName));
    }


    @ApiOperation("查询特定学生信息")
    @RequestMapping(value = "findUserByAccount", method = RequestMethod.GET)//查询所有
    @ResponseBody
    public ReturnT<?> findUserByAccount(@RequestParam(required = false) String account) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserInfo(userService.findUserByAccount(account));
        userDTO.setUserMore(userService.findUserMore(account));
        userDTO.setMsg(boardService.showBoardMsg(account));
        return new ReturnT<>(Constants.SUCCESS, userDTO);
    }

    @ApiOperation("查询可用问卷")
    @RequestMapping(value = "getAvailableQuestions", method = RequestMethod.GET)
    @ResponseBody
    public ReturnT<?> getAvailableQuestions() {
        return new ReturnT<>(Constants.SUCCESS, questionService.getAvailableQuestions());
    }

    @ApiOperation("根据问卷id获取问卷详情")
    @RequestMapping(value = "getQuestionsDetail", method = RequestMethod.GET)
    @ResponseBody
    public ReturnT<?> getQuestionsDetail(Integer qId) {
        return new ReturnT<>(Constants.SUCCESS, questionService.getQuestionsById(qId));
    }

    @ApiOperation("删除问卷,available 置 0")
    @RequestMapping(value = "deleteQuestions", method = RequestMethod.POST)
    @ResponseBody
    public ReturnT<?> deleteQuestions(@RequestBody QuestionPage questionPage) {
        questionService.delete(questionPage);
        return new ReturnT<>(Constants.SUCCESS, Constants.SUCCESS_MSG);
    }

    // 创建新的问卷
    @ApiOperation("创建新的问卷")
    @RequestMapping(value = "addQuestionPage", method = RequestMethod.POST)
    @ResponseBody
    public ReturnT<?> addQuestionPage(@RequestBody QuestionPage questionPage) {
        return questionService.addQuestionPage(questionPage);
    }

    // 添加新题目
    @ApiOperation("添加新题目=等同于更新题库")
    @RequestMapping(value = "addQuestion", method = RequestMethod.POST)
    @ResponseBody
    public ReturnT<?> updateQuestions(@RequestBody Question question) {
        return questionService.addNewQuestion(question);
    }

    @ApiOperation("查询题库内容")
    @RequestMapping(value = "getAllQuestions", method = RequestMethod.GET)//查询所有
    @ResponseBody
    public ReturnT<?> getAllQuestions(@RequestParam(required = false) int index,
                                      @RequestParam(required = false) int perPage) {
        PageHelper.startPage(index, perPage);
        List<Question> questions = questionService.showQuestion();
        return PageUtil.getPageResult(new PageInfo<>(questions));
    }

}
