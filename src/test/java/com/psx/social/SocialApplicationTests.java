package com.psx.social;

import com.psx.social.dao.FriendMapper;
import com.psx.social.dao.UserBaseMapper;
import com.psx.social.entity.FriendRequest;
import com.psx.social.entity.UserBase;
import com.psx.social.service.UserService;
import com.psx.social.util.Verify;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SocialApplicationTests {

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

    @Test
    public void doLogin() {
        UserBase userBase = new UserBase();
        userBase.setAuth(false);
        userBase.setPhone("18539403150");
        userBase.setUser_pwd("123456");
        UserBase res = baseMapper.findByAccountAndPwd(userBase);
        System.out.println(res);
    }
    @Test
    public void registry(){
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
        userService.delUser("2");
    }

    @Test
    public void verifyCode() {
        verify.sendCode("18539403150");
    }
}
