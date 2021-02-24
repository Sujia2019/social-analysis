package com.psx.social.controller;

import com.psx.social.entity.BoardMsg;
import com.psx.social.entity.UserDTO;
import com.psx.social.service.BoardService;
import com.psx.social.service.FriendService;
import com.psx.social.service.SettingService;
import com.psx.social.service.UserService;
import com.psx.social.util.Constants;
import com.psx.social.util.ReturnT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;

@Api("留言板")
@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;
    @Autowired
    UserService userService;
    @Autowired
    SettingService settingService;
    @Autowired
    FriendService friendService;

    @ApiOperation("根据账号展示一个人的留言板")
    @RequestMapping(value = "showBoardMsg", method = RequestMethod.GET)
    @ResponseBody
    public ReturnT<?> showBoard(@RequestParam(required = false) String account,
                                HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        if (account == null || account.equals(user.getUser_account())) {
            return new ReturnT<>(Constants.SUCCESS, boardService.showBoardMsg(user.getUser_account()));
        } else {
            // 无权限 true代表可见
            if (settingService.getUserSettings(account).isShow_board()) {
                return new ReturnT<>(Constants.SUCCESS, boardService.showBoardMsg(user.getUser_account()));
            }
            // 有权限 false
            // 检查好友关系 == 1 为好友
            if (friendService.findRelationByAccount(user.getUser_account(), account) == 1) {
                return new ReturnT<>(Constants.SUCCESS, boardService.showBoardMsg(user.getUser_account()));
            }
            // 非好友关系
            return new ReturnT<>(Constants.SUCCESS, "您没有权限查看", Collections.EMPTY_LIST);
        }
    }

    @ApiOperation("给某人留言")
    @RequestMapping(value = "addBoardMsg", method = RequestMethod.POST)
    @ResponseBody
    public ReturnT<?> addNewBoardMsg(@RequestBody BoardMsg msg) {
        boolean isOk = boardService.addBoardMsg(msg);
        if (isOk) {
            return new ReturnT<>(Constants.SUCCESS, "发送留言成功");
        }
        return new ReturnT<>(Constants.FAIL, "对方设置了留言权限，陌生人暂时无法留言");
    }

    @RequestMapping(value = "newMsgCount")
    @ResponseBody
    public ReturnT<?> newMsgCount(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return new ReturnT<>(Constants.SUCCESS, "新的留言",
                boardService.tipMsgCount(((UserDTO) session.getAttribute("user")).getUser_account()));
    }

    @RequestMapping(value = "newRequestCount")
    @ResponseBody
    public ReturnT<?> newRequestCount(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return new ReturnT<>(Constants.SUCCESS, "新的好友请求",
                boardService.tipRequestCount(((UserDTO) session.getAttribute("user")).getUser_account()));
    }

    @RequestMapping(value = "changeHistoryMsg")
    @ResponseBody
    public ReturnT<?> changeHistoryMsg(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return new ReturnT<>(Constants.SUCCESS, "更新历史留言条数",
                boardService.historyMsgCount(((UserDTO) session.getAttribute("user")).getUser_account()));
    }

    @RequestMapping(value = "changeHistoryRequest")
    @ResponseBody
    public ReturnT<?> changeHistoryRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return new ReturnT<>(Constants.SUCCESS, "更新历史请求条数",
                boardService.historyRequestCount(((UserDTO) session.getAttribute("user")).getUser_account()));
    }
}
