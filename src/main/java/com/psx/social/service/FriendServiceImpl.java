package com.psx.social.service;

import com.psx.social.dao.FriendMapper;
import com.psx.social.dao.UserInfoMapper;
import com.psx.social.entity.FriendRequest;
import com.psx.social.entity.MailDTO;
import com.psx.social.entity.RequestDTO;
import com.psx.social.entity.UserInfo;
import com.psx.social.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    FriendMapper friendMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Override
    public List<UserInfo> showFriends(String account) {
        return friendMapper.friendList(account);
    }

    @Override
    public List<UserInfo> showFriendsRequest(String account) {
        return friendMapper.friendRequestList(account);
    }

    @Override
    public boolean addRequest(FriendRequest friendRequest) {
        String a1 = friendRequest.getAccount1();
        String a2 = friendRequest.getAccount2();
        int relation = findRelationByAccount(a1,a2);
        if (relation > 0) {
            return friendMapper.updateRequest(friendRequest);
        }
        // 重复申请
        if (relation == 0) {
            return true;
        }
        sender(a1, a2, friendRequest.getDetail());
        return friendMapper.insert(friendRequest);
    }

    private void sender(String a1, String a2, String text) {
        MailDTO mail = new MailDTO();
        UserInfo userInfoTo = userInfoMapper.findUserByAccount(a2);
        UserInfo userInfoFrom = userInfoMapper.findUserByAccount(a1);
        mail.setTitle("【xxx平台】您有一条来自【" + userInfoFrom.getSname() + "】发起的的申请消息");
        mail.setTo(userInfoTo.getEmail());
        mail.setText("您有一条来自" + userInfoFrom.getSname() + "的消息，申请内容为：\n\n" + text);
    }

    @Override
    public boolean acceptRequest(String account1, String account2) {
        FriendRequest request = new FriendRequest();
        request.setAccount1(account1);
        request.setAccount2(account2);
        request.setAddStatus(1);
        friendMapper.updateRequest(request);
        request.setAccount1(account2);
        request.setAccount2(account1);
        senderAccept(account1);
        return friendMapper.insert(request);
    }

    private void senderAccept(String account) {
        MailDTO mail = new MailDTO();
        UserInfo userInfo = userInfoMapper.findUserByAccount(account);
        mail.setTitle("【xxx平台】您有一条来自【" + userInfo.getSname() + "】的好友消息");
        mail.setTo(userInfo.getEmail());
        mail.setText("您有一条来自" + userInfo.getSname() + "的好友消息\n\n" + "我们已经是好友啦！");
    }

    @Override
    public boolean refuseRequest(FriendRequest friendRequest) {
        return friendMapper.updateRequest(friendRequest);
    }

    @Override
    public boolean delFriend(String account1, String account2) {
        FriendRequest request = new FriendRequest();
        request.setAccount1(account1);
        request.setAccount2(account2);
        // 双向直接删除
        friendMapper.delFriends(request);
        request.setAccount1(account2);
        request.setAccount2(account1);
        friendMapper.delFriends(request);
        return true;
    }

    @Override
    public int findFriendsCount(String account) {
        return friendMapper.countFriends(account);
    }

    @Override
    public int countRequest(String account) {
        return friendMapper.countRequest(account);
    }

    @Override
    public int findRelationByAccount(String account1, String account2) {
        FriendRequest friendRequest = friendMapper.findRelationByAccount(account1, account2);
        if (friendRequest != null) {
            return friendRequest.getAddStatus();
        }
        return -1;
    }

    @Override
    public boolean update(FriendRequest request) {
        int flag = request.getAddStatus();
        String ac1 = request.getAccount1();
        String ac2 = request.getAccount2();
        switch (flag) {
            case -1:
                delFriend(ac1, ac2);
                return true;
            case 0:
                addRequest(request);
                return true;
            case 1:
                acceptRequest(ac1, ac2);
                return true;
            case 2:
                refuseRequest(request);
                return true;
            default:
                return false;
        }
    }

    @Override
    public List<RequestDTO> getRequest(String account, int flag) {
        List<FriendRequest> requests = new ArrayList<>();
        // 我发起的
        if (flag == Constants.ME) {
            requests = friendMapper.findRequest1(account);
            return convertToDTO2(requests);
        } else if (flag == Constants.OTHER) {
            requests = friendMapper.findRequest2(account);
            return convertToDTO1(requests);
        }
        return Collections.EMPTY_LIST;
    }

    private List<RequestDTO> convertToDTO1(List<FriendRequest> requests) {
        List<RequestDTO> requestDTOS = new ArrayList<>();
        for (FriendRequest request : requests) {
            RequestDTO requestDTO = new RequestDTO();
            requestDTO.setSelfAccount(request.getAccount2());
            requestDTO.setAccount(request.getAccount1());
            requestDTO.setName(userInfoMapper.findUserByAccount(request.getAccount1()).getSname());
            requestDTO.setDetail(request.getDetail());
            requestDTOS.add(requestDTO);
        }
        return requestDTOS;
    }

    private List<RequestDTO> convertToDTO2(List<FriendRequest> requests) {
        List<RequestDTO> requestDTOS = new ArrayList<>();
        for (FriendRequest request : requests) {
            RequestDTO requestDTO = new RequestDTO();
            requestDTO.setSelfAccount(request.getAccount1());
            requestDTO.setAccount(request.getAccount2());
            requestDTO.setName(userInfoMapper.findUserByAccount(request.getAccount2()).getSname());
            requestDTO.setDetail(request.getDetail());
            requestDTOS.add(requestDTO);
        }
        return requestDTOS;
    }
}
