package com.psx.social.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

// 答题结果返回
@ApiModel("分析结果返回")
@Data
public class ResultMsg implements Serializable {
    @ApiModelProperty("综合分析评级")
    private String rate; // 评级
    @ApiModelProperty("综合分析内容")
    private String detail; // 内容
    @ApiModelProperty("综合分析分数")
    private int score; // 分数
    @ApiModelProperty("活跃度")
    private long active; // 活跃度
    @ApiModelProperty("活跃度排名")
    private int activeRank;
    @ApiModelProperty("好友数")
    private int friendCount;
    @ApiModelProperty("好友对应文案")
    private String friendDetail;
    @ApiModelProperty("留言互动对应文案")
    private String boardDetail;
    @ApiModelProperty("日常交流文案")
    private String dailyDetail;
    @ApiModelProperty("正面言论数")
    private long positive;
    @ApiModelProperty("负面言论数")
    private long negative;
    @ApiModelProperty("总发言数")
    private long msgCount;
    private String user_account;
}
