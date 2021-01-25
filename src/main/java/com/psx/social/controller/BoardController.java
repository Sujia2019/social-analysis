package com.psx.social.controller;

import com.psx.social.entity.BoardMsg;
import com.psx.social.entity.UserDTO;
import com.psx.social.service.BoardService;
import com.psx.social.service.FriendService;
import com.psx.social.service.SettingService;
import com.psx.social.service.UserService;
import com.psx.social.util.Constants;
import com.psx.social.util.ReturnT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

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

    @RequestMapping(value="addBoardMsg")
    @ResponseBody
    public ReturnT<?> addNewBoardMsg(@RequestBody BoardMsg msg){
        boolean isOk = boardService.addBoardMsg(msg);
        if (isOk){
            return new ReturnT<>(Constants.SUCCESS,"发送留言成功");
        }
        return new ReturnT<>(Constants.FAIL,"对方设置了留言权限，陌生人暂时无法留言");
    }

}
