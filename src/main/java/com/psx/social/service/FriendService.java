package com.psx.social.service;


import com.psx.social.entity.BoardMsg;
import com.psx.social.entity.FriendRequest;
import com.psx.social.entity.RequestDTO;
import com.psx.social.entity.UserInfo;

import java.util.List;

public interface FriendService {
    // 获取好友信息
    List<UserInfo> showFriends(String account);
    // 查看申请好友的用户信息
    List<UserInfo> showFriendsRequest(String account);
    // 添加好友申请
    boolean addRequest(FriendRequest friendRequest);
    // 接受好友申请
    boolean acceptRequest(String account1,String account2);

    // 拒绝好友申请
    boolean refuseRequest(FriendRequest friendRequest);

    // 删除好友
    boolean delFriend(String account1, String account2);

    // 好友数量
    int findFriendsCount(String account);

    // 好友申请数量
    int countRequest(String account);

    // 查询好友关系
    int findRelationByAccount(String account1, String account2);

    // 通用修改好友关系
    boolean update(FriendRequest friendRequest);

    List<RequestDTO> getRequest(String account, int flag);
}
