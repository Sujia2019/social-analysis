package com.psx.social.entity;

import lombok.Data;

// 答题结果返回
@Data
public class ResultMsg {
    private String rate;// 评级
    private String detail;// 内容
    private int score;// 分数
    private String user_account;
}
