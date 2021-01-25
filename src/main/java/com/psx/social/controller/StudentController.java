package com.psx.social.controller;
import com.psx.social.entity.UserBase;
import com.psx.social.entity.UserDTO;
import com.psx.social.service.BoardService;
import com.psx.social.service.FriendService;
import com.psx.social.service.SettingService;
import com.psx.social.service.UserService;
import com.psx.social.util.Constants;
import com.psx.social.util.ReturnT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


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

    // 学生注册
    @RequestMapping(value = "doRegistry", method = RequestMethod.POST)
    @ResponseBody
    public ReturnT<?> doRegistry(@RequestBody UserBase userBase, HttpServletRequest request) {
        return userService.doRegistry(userBase);
    }

    @RequestMapping(value = "showInfo", method = RequestMethod.GET)
    @ResponseBody
    public ReturnT<?> showInfo(@RequestParam(required = false) String account,
                               HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
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

}


