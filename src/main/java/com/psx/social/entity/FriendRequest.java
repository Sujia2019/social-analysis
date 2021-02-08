package com.psx.social.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("好友申请")
@Data
public class FriendRequest implements Serializable {
    private String account1;
    private String account2;
    private String detail;  // 申请理由
    @ApiModelProperty("表示好友关系状态，【-1】表示删除；【0】表示已发送申请待处理；【1】表示接受好友请求，已通过；【2】表示拒绝好友请求")
    private int addStatus;
    //1:已通过
    //0:待处理
    //2:被拒绝
    //-1：待删除
}
