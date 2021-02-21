package com.psx.social.controller;

import com.psx.social.entity.FriendRequest;
import com.psx.social.service.FriendService;
import com.psx.social.service.SettingService;
import com.psx.social.util.Constants;
import com.psx.social.util.ReturnT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("好友关系")
@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    FriendService friendService;
    @Autowired
    SettingService settingService;

    @ApiOperation("发送好友申请(创建一条关系)")
    @RequestMapping(value = "addRequest", method = RequestMethod.POST)
    @ResponseBody
    public ReturnT<?> addRequest(@RequestBody FriendRequest request){
        String a1 = request.getAccount1();
        String a2 = request.getAccount2();
        // 先检查是否已经为好友
        if (friendService.findRelationByAccount(a1,a2) == 1){
            return new ReturnT<>(Constants.FAIL, "你们已经是好友了，不要重复添加");
        }
        // 是否接受好友邀请
        if (settingService.getUserSettings(a2).isReceive_friend()) {
            friendService.addRequest(request);
            return new ReturnT<>(Constants.SUCCESS, "成功发送好友邀请");
        } else {
            return new ReturnT<>(Constants.FAIL, "对方开启了隐私设置，暂不接受好友请求");
        }
    }

    @ApiOperation("根据账号获得所有好友")
    @RequestMapping(value = "getFriends", method = RequestMethod.GET)
    @ResponseBody
    public ReturnT<?> getFriendInfo(@RequestParam(required = false) String account) {
        // TODO 不传account 从session查，session如果过期就跳登录页
        return new ReturnT<>(Constants.SUCCESS, friendService.showFriends(account));
    }

    @ApiOperation("修改好友关系，包括删除，接受好友请求都是一样")
    @RequestMapping(value = "updateRel", method = RequestMethod.POST)
    @ResponseBody
    public ReturnT<?> update(@RequestBody(required = false) FriendRequest request) {
        // TODO 不传account 从session查，session如果过期就跳登录页
        return new ReturnT<>(Constants.SUCCESS, friendService.update(request));
    }

//    @ApiOperation("修改好友关系，包括删除，接受好友请求都是一样")
//    @RequestMapping(value = "getRequest", method = RequestMethod.GET)
//    @ResponseBody
//    public ReturnT<?> getRequest(@RequestBody(required = false) String account) {
//        // TODO 不传account 从session查，session如果过期就跳登录页
//        return new ReturnT<>(Constants.SUCCESS, friendService.(account));
//    }
}
