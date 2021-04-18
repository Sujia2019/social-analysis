package com.psx.social;

import com.psx.social.controller.IndexController;
import com.psx.social.dao.ChatRoomMapper;
import com.psx.social.dao.FriendMapper;
import com.psx.social.dao.SettingsMapper;
import com.psx.social.dao.UserBaseMapper;
import com.psx.social.entity.*;
import com.psx.social.service.UserService;
import com.psx.social.util.MailConfig;
import com.psx.social.util.ReturnT;
import com.psx.social.util.Verify;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
class SocialApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger("ChatRoom");

    @Test
    void contextLoads() {
    }

    @Autowired
    UserBaseMapper baseMapper;
    @Autowired
    FriendMapper friendMapper;
    @Autowired
    UserService userService;
    @Autowired
    Verify verify;
    @Autowired
    IndexController indexController;
    @Autowired
    SettingsMapper settingsMapper;
    @Autowired
    MailConfig mailConfig;
    @Autowired
    ChatRoomMapper chatRoomMapper;

    @Test
    public void doLogin() {
        UserBase userBase = new UserBase();
        userBase.setAuth(false);
        userBase.setUser_account("test");
        userBase.setUser_pwd("123");
        UserDTO res = userService.doLogin(userBase);
        System.out.println(res);
    }

    @Test
    public void loginDTO() {
        UserBase userBase = new UserBase();
        userBase.setAuth(false);
        userBase.setUser_account("test");
        userBase.setUser_pwd("123");
        System.out.println(baseMapper.findByAccountAndPwd(userBase));
    }

    @Test
    public void registry() {
        UserBase user1 = new UserBase();
        user1.setAuth(false);
        user1.setUser_account("1");
        user1.setUser_pwd("123456");
        userService.doRegistry(user1);

        UserBase user2 = new UserBase();
        user2.setAuth(false);
        user2.setUser_account("2");
        user2.setUser_pwd("123456");
        userService.doRegistry(user2);

    }

    @Test
    public void doRequest(){
//        FriendRequest request = new FriendRequest();
//        request.setAccount1("1");
//        request.setAccount2("2");
//        request.setDetail("test");
//        request.setAddStatus(0);
//        friendMapper.insert(request);
        System.out.println(friendMapper.friendRequestList("1"));
    }

    @Test
    public void delete() {
        userService.delUser("test44");
    }

    @Test
    public void verifyCode() {
        verify.sendCode("18539403150");
    }

    @Test
    public void log() {
        LOGGER.info("聊天记录，来自【{}】,内容：{}", "xxx", "xxx");
    }

    @Test
    public void likeQuery() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUser_account("sujia");
        System.out.println(userService.findUsersInfoByLikeQuery(userInfo));
    }

    @Test
    public void settings() {
        Settings settings = new Settings();
        settings.setUser_account("test11");
        settings.setReceive_board(false);
        settings.setReceive_friend(false);
        settings.setSend_notice(false);
        settings.setShow_board(false);
        settingsMapper.update(settings);
    }

    @Test
    public void mailSender() {
        MailDTO mail = new MailDTO();
        mail.setTitle("您有一条消息");
        mail.setText("测试");
        mail.setTo("18539403150@163.com");
        mailConfig.send(mail);
    }

    @Test
    public void getFriends() {
        System.out.println(friendMapper.friendList("test"));
    }


    @Test
    public void chat() {
        ChatRoom chatRoom = chatRoomMapper.getChatInfo("test");
        Timestamp t1 = chatRoom.getCreate_time();
        Timestamp t2 = chatRoom.getLast_modify();
        long time = t2.getTime() - t1.getTime();

        long activity = chatRoom.getMsg_count() * 86400000L / time;

        System.out.println(chatRoom);
        System.out.println(time);
        System.out.println(activity);
    }
}
