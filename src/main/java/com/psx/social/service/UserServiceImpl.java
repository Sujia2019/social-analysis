package com.psx.social.service;

import com.psx.social.dao.*;
import com.psx.social.entity.*;
import com.psx.social.util.Constants;
import com.psx.social.util.ReturnT;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    FriendMapper friendMapper;
    @Autowired
    UserBaseMapper userBaseMapper;
    @Autowired
    UserMoreMapper userMoreMapper;
    @Autowired
    SettingsMapper settingsMapper;
    @Autowired
    QuestionMapper questionMapper;


    @Override
    public UserDTO doLogin(UserBase user) {
        String account0 = user.getUser_account();
        if (isMobilePhone(account0)) {
            user.setUser_account(null);
            user.setPhone(account0);
        } else if (isEmail(account0)) {
            user.setUser_account(null);
            user.setEmail(account0);
        }
        user.setUser_pwd(DigestUtils.md5Hex(user.getUser_pwd()));
        user = userBaseMapper.findByAccountAndPwd(user);
        if (user != null) {
            String account = user.getUser_account();
            UserDTO userDTO = new UserDTO();
            userDTO.setUser_account(account);
            userDTO.setUserInfo(userInfoMapper.findUserByAccount(account));
            userDTO.setUserMore(userMoreMapper.findByAccount(account));
            userDTO.setSettings(settingsMapper.findByAccount(account));
            return userDTO;
        }
        return null;
    }

    @Override
    public ReturnT<?> doRegistry(UserBase user) {
        String account;
        turnToMD5(user);
        if (!Strings.isBlank(user.getUser_account())){
            if(null != userBaseMapper.findByAccount(user.getUser_account()))
                return new ReturnT<>(Constants.FAIL,"注册失败,该账号已经被注册过");
        }
        if (Strings.isBlank(user.getUser_account())){
            Long create = System.currentTimeMillis();
            LOGGER.info(">>>>>创建用户时间：{}",create);
            account = "#"+create+"#";
            user.setUser_account(account);
        }
        // 检查该手机号是否被注册过
        if (!Strings.isBlank(user.getPhone())){
            if(null !=userBaseMapper.findByPhone(user.getPhone()))
                return new ReturnT<>(Constants.FAIL,"注册失败,该手机号已经被注册过");
        }
        if (!Strings.isBlank(user.getEmail())){
            if(null != userBaseMapper.findByEmail(user.getEmail()))
                return new ReturnT<>(Constants.FAIL,"注册失败,该邮箱已经被注册过");
        }

        userBaseMapper.insert(user);
        UserInfo userInfo = new UserInfo();
        userInfo.setSname("用户" + System.currentTimeMillis());
        userInfo.setEmail(user.getEmail());
        userInfo.setPhone(user.getPhone());
        userInfo.setUser_account(user.getUser_account());
        userInfoMapper.insert(userInfo);
        Settings settings = new Settings();
        settings.setUser_account(user.getUser_account());
        settingsMapper.insert(settings);
        UserMore userMore = new UserMore();
        userMore.setUser_account(user.getUser_account());
        userMore.setIsFinishedQ(false);
        userMore.setMsgCount(0);
        userMore.setRequestCount(0);
        userMore.setScores(0);
        userMoreMapper.insert(userMore);
        // 1 MD5加密
        // 2 如果账号为空，说明此人用手机号或者邮箱注册的，账号按当前时间设置
        // 3 初始化基本信息，设置等
        return new ReturnT<>(Constants.SUCCESS, "注册成功", user);
    }

    @Override
    public boolean hasAccount(String account) {
        return userBaseMapper.checkAccount(account) == 0;
    }

    @Override
    public ReturnT<?> doChangePwd(UserBase userBase) {
        // 最好前端base64编码 后端解码 再转md5
        turnToMD5(userBase);
        userBaseMapper.updatePwd(userBase);
        return new ReturnT<>(Constants.SUCCESS, "修改成功");
    }

    @Override
    public boolean delUser(String account) {
        userBaseMapper.delete(account);
        userInfoMapper.delete(account);
        // TODO 删除好友关系
        userMoreMapper.delete(account);
        settingsMapper.delete(account);
        return true;
    }

    @Override
    public List<UserInfo> findAll() {
        return userInfoMapper.findAll();
    }

    @Override
    public List<UserInfo> findUsersByAccount(String account) {
        return userInfoMapper.findUsersByAccount(account);
    }

    @Override
    public List<UserInfo> findUsersByName(String name) {
        return userInfoMapper.findUsersByName(name);
    }

    @Override
    public List<UserInfo> findUsersByAge(int age) {
        return userInfoMapper.findUsersByAge(age);
    }

    @Override
    public List<UserInfo> findUsersByCollage(String collage) {
        return userInfoMapper.findUsersByCollage(collage);
    }

    @Override
    public List<UserInfo> findUsersByMajor(String major) {
        return userInfoMapper.findUsersByMajor(major);
    }

    @Override
    public List<UserInfo> findUsersInfoByLikeQuery(UserInfo userInfo) {
        return userInfoMapper.findUsersInfoByLikeQuery(userInfo);
    }

    @Override
    public UserInfo findUserByAccount(String account) {
        return userInfoMapper.findUserByAccount(account);
    }

    @Override
    public boolean updateUser(UserInfo user) {
        return userInfoMapper.updateUser(user);
    }

    @Override
    public UserMore addScores(String account, Integer scores) {
        userMoreMapper.setScore(account,scores);
        return userMoreMapper.findByAccount(account);
    }

    @Override
    public ResultMsg returnResult(String account) {
        int scores = userMoreMapper.findScoreByAccount(account);
        ResultMsg res = new ResultMsg();
        String rate;
        if(scores>=48){
            rate="A";
            res.setDetail(Constants.A);
        }else if(scores>=37){
            rate="B";
            res.setDetail(Constants.B);
        }else if(scores>=27){
            rate="C";
            res.setDetail(Constants.C);
        }else {
            rate="D";
            res.setDetail(Constants.D);
        }
        res.setUser_account(account);
        res.setRate(rate);
        res.setScore(scores);
        return res;
    }


    @Override
    public boolean isFinished(String account) {
        return userMoreMapper.isFinished(account);
    }

    @Override
    public UserInfo getPrivateInfo(String account) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUser_account(account);
        userInfo.setPhone(Constants.PRIVATE);
        userInfo.setEmail(Constants.PRIVATE);
        userInfo.setCollage(Constants.PRIVATE);
        userInfo.setBirth(Constants.PRIVATE);
        userInfo.setGender(-1);
        userInfo.setMajor(Constants.PRIVATE);
        userInfo.setSage(-1);
        userInfo.setSname(Constants.PRIVATE);
        userInfo.setDetail(Constants.PRIVATE);
        return userInfo;
    }

    @Override
    public UserMore findUserMore(String account) {
        return userMoreMapper.findByAccount(account);
    }

    private UserBase turnToMD5(UserBase userBase) {
        userBase.setUser_pwd(DigestUtils.md5Hex(userBase.getUser_pwd()));
        return userBase;
    }

    private boolean isMobilePhone(String input) {
        String reg = "^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$";
        return Pattern.matches(reg, input);
    }

    private boolean isEmail(String input) {
        String reg = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return Pattern.matches(reg, input);
    }
}
