package com.psx.social.controller;

import com.psx.social.entity.BoardMsg;
import com.psx.social.service.BoardService;
import com.psx.social.service.SettingService;
import com.psx.social.service.UserService;
import com.psx.social.util.Constants;
import com.psx.social.util.ReturnT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;
    @Autowired
    UserService userService;
    @Autowired
    SettingService settingService;

    @RequestMapping(value="showBoardMsg")
    @ResponseBody
    public ReturnT<?> getBoardMsg(@RequestParam String account){
        return new ReturnT<>(Constants.SUCCESS,boardService.showBoardMsg(account));
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
