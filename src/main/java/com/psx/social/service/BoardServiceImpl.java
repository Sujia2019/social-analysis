package com.psx.social.service;

import com.psx.social.dao.BoardMapper;
import com.psx.social.dao.FriendMapper;
import com.psx.social.dao.SettingsMapper;
import com.psx.social.entity.BoardMsg;
import com.psx.social.entity.FriendRequest;
import com.psx.social.entity.MailDTO;
import com.psx.social.entity.Tips;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class BoardServiceImpl implements BoardService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BoardServiceImpl.class);
    @Autowired
    BoardMapper boardMapper;
    @Autowired
    FriendMapper friendMapper;
    @Autowired
    SettingsMapper settingsMapper;
    @Autowired
    FriendService friendService;
    @Autowired
    UserService userService;

    private int countMsg = 0;
    private int countRequest = 0;
    private int times = 0;

    @Override
    public boolean addBoardMsg(BoardMsg msg) {
        String account = msg.getUser_account(); // 目标用户账号
        // 如果允许接受陌生留言留言在关闭状态下
        if (!settingsMapper.findByAccount(account).isReceive_board()) {
            String from = msg.getFrom_account();
            // 只有好友才可以留言
            if (friendService.findRelationByAccount(account, from) == 1) {
                sender(msg);
                return boardMapper.insertBoardMsg(msg);
            } else
                return false;
        }
        sender(msg);
        return boardMapper.insertBoardMsg(msg);
    }

    private void sender(BoardMsg msg) {
        MailDTO mail = new MailDTO();
        String address = userService.findUserByAccount(msg.getUser_account()).getEmail();
        mail.setTitle("【xxx平台】您有一条来自【" + msg.getFrom_name() + "】的留言消息");
        mail.setTo(address);
        mail.setText("您有一条来自【" + msg.getFrom_name() + "】的留言消息\n\n" + msg.getBoard_text());
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
        countMsg = boardMapper.countBoardMsg(account);
        LOGGER.info("历史留言条数：{}", countMsg);
        Tips tips = new Tips();
        tips.setAccount(account);
        tips.setCount(countMsg);
        return boardMapper.setHistoryMsgCount(tips);
    }

    @Override
    public int historyRequestCount(String account) {
        countRequest = friendMapper.countRequest(account);
        LOGGER.info("历史请求条数：{}", countRequest);
        Tips tips = new Tips();
        tips.setAccount(account);
        tips.setCount(countRequest);
        return friendMapper.setHistoryRequestCount(tips);
    }
}
