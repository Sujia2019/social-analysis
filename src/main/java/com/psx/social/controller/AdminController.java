package com.psx.social.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.psx.social.entity.AnalyzingData;
import com.psx.social.entity.UserInfo;
import com.psx.social.service.QuestionService;
import com.psx.social.service.UserService;
import com.psx.social.util.Constants;
import com.psx.social.util.PageUtil;
import com.psx.social.util.ReturnT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    UserService userService;
    @Autowired
    QuestionService questionService;

    // 查询所有学生
    @RequestMapping(value = "findAll", method = RequestMethod.GET)//查询所有
    @ResponseBody
    public ReturnT<?> findAll(@RequestParam(required = false) int index,
                              @RequestParam(required = false) int perPage) {
        PageHelper.startPage(index, perPage);
        List<UserInfo> users = userService.findAll();
        return PageUtil.getPageResult(new PageInfo<>(users));
    }

    @RequestMapping("findUsersByAccount")
    @ResponseBody
    public ReturnT<?> findUsersByAccount(@RequestParam String account,
                                         @RequestParam(required = false) int index,
                                         @RequestParam(required = false) int perPage) {
        PageHelper.startPage(index, perPage);
        List<UserInfo> users = userService.findUsersByAccount(account);
        return PageUtil.getPageResult(new PageInfo<>(users));
    }

    @RequestMapping("findUsersByAge")
    @ResponseBody
    public ReturnT<?> findUsersByAge(@RequestParam int age,
                                         @RequestParam(required = false) int index,
                                         @RequestParam(required = false) int perPage) {
        PageHelper.startPage(index, perPage);
        List<UserInfo> users = userService.findUsersByAge(age);
        return PageUtil.getPageResult(new PageInfo<>(users));
    }

    @RequestMapping("findUsersByCollage")
    @ResponseBody
    public ReturnT<?> findUsersByCollage(@RequestParam String collage,
                                     @RequestParam(required = false) int index,
                                     @RequestParam(required = false) int perPage) {
        PageHelper.startPage(index, perPage);
        List<UserInfo> users = userService.findUsersByCollage(collage);
        return PageUtil.getPageResult(new PageInfo<>(users));
    }

    @RequestMapping("findUsersByMajor")
    @ResponseBody
    public ReturnT<?> findUsersByMajor(@RequestParam String major,
                                         @RequestParam(required = false) int index,
                                         @RequestParam(required = false) int perPage) {
        PageHelper.startPage(index, perPage);
        List<UserInfo> users = userService.findUsersByMajor(major);
        return PageUtil.getPageResult(new PageInfo<>(users));
    }

    @RequestMapping("findUsersByName")
    @ResponseBody
    public ReturnT<?> findUsersByName(@RequestParam String name,
                                       @RequestParam(required = false) int index,
                                       @RequestParam(required = false) int perPage) {
        PageHelper.startPage(index, perPage);
        List<UserInfo> users = userService.findUsersByName(name);
        return PageUtil.getPageResult(new PageInfo<>(users));
    }

    @RequestMapping("delete")
    @ResponseBody
    public ReturnT<?> delete(@RequestParam String account) {
        if (userService.delUser(account))
            return new ReturnT<>(Constants.SUCCESS,"删除成功");
        else {
            return new ReturnT<>(Constants.FAIL,"删除失败");
        }
    }

    @RequestMapping(value = "finishedPercent")
    @ResponseBody
    public ReturnT<?> finishedPercent() {
        AnalyzingData data = questionService.analyzingRes();
        LOGGER.info("答题情况：{}",data.toString());
        return new ReturnT<>(Constants.SUCCESS,"查询答题完成情况成功",data);

    }

}
