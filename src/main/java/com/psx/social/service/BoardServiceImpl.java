package com.psx.social.service;

import com.psx.social.dao.BoardMapper;
import com.psx.social.dao.FriendMapper;
import com.psx.social.dao.SettingsMapper;
import com.psx.social.entity.BoardMsg;
import com.psx.social.entity.FriendRequest;
import com.psx.social.entity.Tips;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardMapper boardMapper;
    @Autowired
    FriendMapper friendMapper;
    @Autowired
    SettingsMapper settingsMapper;
    @Autowired
    FriendService friendService;

    private int countMsg=0;
    private int countRequest=0;
    private int times=0;

    @Override
    public boolean addBoardMsg(BoardMsg msg) {
        String account = msg.getUser_account();
        // 如果接受留言在关闭状态下
        if (!settingsMapper.findByAccount(account).isReceive_board()){
            String from = msg.getFrom_account();
            // 只有好友才可以留言
            if(friendService.findRelationByAccount(account,from) == 1)
                return boardMapper.insertBoardMsg(msg);
            else
                return false;
        }
        return boardMapper.insertBoardMsg(msg);
    }

    @Override
    public List<BoardMsg> showBoardMsg(String account) {
        return boardMapper.showBoardMsg(account);
    }

    @Override
    public int boardMsgCount(String account) {
        return boardMapper.countBoardMsg(account);
    }

    @Override
    public int tipMsgCount(String account) {
        int count1=boardMapper.countBoardMsg(account);
        countMsg=boardMapper.historyMsgCount(account);
        if(count1>countMsg){
            return count1-countMsg;
        }else{
            return 0;
        }
    }

    @Override
    public int tipRequestCount(String account) {
        int count1=friendMapper.countRequest(account);
        countRequest=friendMapper.historyRequestCount(account);
        if(count1>countRequest){
            return count1-countRequest;
        }else{
            return 0;
        }
    }

    @Override
    public int historyMsgCount(String account) {
        countMsg=boardMapper.countBoardMsg(account);
        System.out.println(countMsg);
        Tips tips = new Tips();
        tips.setAccount(account);
        tips.setCount(countMsg);
        return boardMapper.setHistoryMsgCount(tips);
    }

    @Override
    public int historyRequestCount(String account) {
        countRequest=friendMapper.countRequest(account);
        System.out.println(countRequest);
        Tips tips = new Tips();
        tips.setAccount(account);
        tips.setCount(countRequest);
        return friendMapper.setHistoryRequestCount(tips);
    }
}
