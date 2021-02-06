package com.psx.social.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

// 答题结果返回
@ApiModel("答题结果返回")
@Data
public class ResultMsg implements Serializable {
    private String rate;// 评级
    private String detail;// 内容
    private int score;// 分数
    private String user_account;
}
