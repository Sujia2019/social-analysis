package com.psx.social.service;

import com.psx.social.entity.BoardMsg;
import com.psx.social.entity.UserMore;

import java.util.List;

// 包含留言板，提示相关
public interface BoardService {
    // 添加留言
    boolean addBoardMsg(BoardMsg msg);
    // 展示留言板
    List<BoardMsg> showBoardMsg(String account);

    // 留言总条数 select count()
    int boardMsgCount(String account);
    // 提示留言数（没看的）>>> 总-历史
    int tipMsgCount(String account);
    // 提示好友申请数（没处理的）
    int tipRequestCount(String account);
    // 历史留言条目（专门记录在board里）
    int historyMsgCount(String account);
    // 历史请求条目（同）
    int historyRequestCount(String account);
}
