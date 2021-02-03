package com.psx.social.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel("用户其他信息")
@Data
public class UserMore implements Serializable {
    private String user_account;
    private boolean IsFinishedQ;
    private int scores;
    private int msgCount;
    private int requestCount;
}
