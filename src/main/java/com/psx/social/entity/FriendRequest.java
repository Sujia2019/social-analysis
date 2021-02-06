package com.psx.social.entity;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel("好友申请")
@Data
public class FriendRequest implements Serializable {
    private String account1;
    private String account2;
    private String detail;  // 申请理由
    private int addStatus;
    //1:已通过
    //0:待处理
    //2:被拒绝
}
