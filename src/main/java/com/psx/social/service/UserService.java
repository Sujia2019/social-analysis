package com.psx.social.service;

import com.psx.social.entity.*;
import com.psx.social.util.ReturnT;

import java.util.List;

// 关于用户信息
public interface UserService {
    public UserDTO doLogin(UserBase user);
    public ReturnT<?> doRegistry(UserBase user);

    boolean delUser(String account);
    List<UserInfo> findAll();
    List<UserInfo> findUsersByAccount(String account);
    List<UserInfo> findUsersByName(String name);
    List<UserInfo> findUsersByAge(int age);
    List<UserInfo> findUsersByCollage(String collage);
    List<UserInfo> findUsersByMajor(String major);
    List<UserInfo> findUsersInfoByLikeQuery(UserInfo userInfo);
    UserInfo findUserByAccount(String account);
    boolean updateUser(UserInfo user);

    // 查找一个用户的扩展信息
    UserMore findUserBoard(String account);
    // 计算分数
    UserMore addScores(String account, List<ReturnAnswer> list);
    ResultMsg returnResult(String account);
    boolean isFinished(String account);

}
