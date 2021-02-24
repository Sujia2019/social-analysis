package com.psx.social.controller;
import com.psx.social.entity.UserBase;
import com.psx.social.entity.UserDTO;
import com.psx.social.entity.UserInfo;
import com.psx.social.service.BoardService;
import com.psx.social.service.FriendService;
import com.psx.social.service.SettingService;
import com.psx.social.service.UserService;
import com.psx.social.util.Constants;
import com.psx.social.util.ReturnT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api("学生端")
@RestController
@RequestMapping("/stu")
public class StudentController {
    @Autowired
    UserService userService;
    @Autowired
    SettingService settingService;
    @Autowired
    BoardService boardService;
    @Autowired
    FriendService friendService;
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    // 学生注册
    @ApiOperation("学生注册")
    @RequestMapping(value = "doRegistry", method = RequestMethod.POST)
    @ResponseBody
    public ReturnT<?> doRegistry(@RequestBody UserBase userBase) {
        return userService.doRegistry(userBase);
    }

    @ApiOperation("根据账号展示个人信息")
    @RequestMapping(value = "showInfo", method = RequestMethod.GET)
    @ResponseBody
    public ReturnT<?> showInfo(@RequestParam(required = false) String account,
                               HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        if (null == user) {
            return new ReturnT<>(Constants.FAIL, "请登录");
        }
        // 表示查看自己
        if (account == null || account.equals(user.getUser_account())) {
            return new ReturnT<>(Constants.SUCCESS, user.getUserInfo());
        } else {
            // 无权限 true代表可见
            if (settingService.getUserSettings(account).isShow_board()) {
                return new ReturnT<>(Constants.SUCCESS, userService.findUserByAccount(account));
            }
            // 有权限 false
            // 检查好友关系 == 1 为好友
            if (friendService.findRelationByAccount(user.getUser_account(), account) == 1) {
                return new ReturnT<>(Constants.SUCCESS, userService.findUserByAccount(account));
            }
            // 非好友关系
            return new ReturnT<>(Constants.SUCCESS, userService.getPrivateInfo(account));
        }
    }

    @ApiOperation("更新用户信息")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ReturnT<?> updateInfo(@RequestBody UserInfo userInfo, HttpServletRequest request) {
        userService.updateUser(userInfo);
        System.out.println(userInfo.toString());
        HttpSession session = request.getSession();
        UserDTO userDto = (UserDTO) session.getAttribute("user");
        userDto.setUserInfo(userInfo);
        session.setAttribute("user", userDto);
        return new ReturnT<>(Constants.SUCCESS, Constants.SUCCESS_MSG);
    }

    @ApiOperation("获取登录用户的信息")
    @RequestMapping(value = "search", method = RequestMethod.GET)
    @ResponseBody
    public ReturnT<?> search(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDTO userDto = (UserDTO) session.getAttribute("user");
        LOGGER.info("【查询用户session信息】{}", userDto);
        return new ReturnT<>(Constants.SUCCESS, Constants.SUCCESS_MSG, userDto);
    }

    @ApiOperation("获取某用户的信息")
    @RequestMapping(value = "getByAccount", method = RequestMethod.GET)
    @ResponseBody
    public ReturnT<?> getByAccount(String account) {
        return new ReturnT<>(Constants.SUCCESS, Constants.SUCCESS_MSG, userService.findUserByAccount(account));
    }

}


