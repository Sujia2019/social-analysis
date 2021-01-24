package com.psx.social.entity;


import lombok.Data;

@Data
public class FriendRequest {
    private String account1;
    private String account2;
    private String detail;  // 申请理由
    private int addStatus;
    //1:已通过
    //0:待处理
    //2:被拒绝
}
