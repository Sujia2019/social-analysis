package com.psx.social.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.psx.social.entity.AnalyzingData;
import com.psx.social.entity.UserInfo;
import com.psx.social.service.QuestionService;
import com.psx.social.service.UserService;
import com.psx.social.util.ChatListener;
import com.psx.social.util.Constants;
import com.psx.social.util.PageUtil;
import com.psx.social.util.ReturnT;
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
    public ReturnT<?> findUsersByLikeQuery(@RequestBody UserInfo userInfo,
                                           @RequestParam(required = false) int index,
                                           @RequestParam(required = false) int perPage) {
        PageHelper.startPage(index, perPage);
        List<UserInfo> users = userService.findUsersInfoByLikeQuery(userInfo);
        return PageUtil.getPageResult(new PageInfo<>(users));
    }

    @ApiOperation("根据账号删除")
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
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

}
