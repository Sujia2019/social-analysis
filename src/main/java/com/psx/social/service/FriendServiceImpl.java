package com.psx.social.service;

import com.psx.social.dao.FriendMapper;
import com.psx.social.dao.UserInfoMapper;
import com.psx.social.entity.FriendRequest;
import com.psx.social.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (relation > 0){
            return friendMapper.updateRequest(friendRequest);
        }
        // 重复申请
        if (relation == 0){
            return true;
        }
        return friendMapper.insert(friendRequest);
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
        return friendMapper.insert(request);
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
}
