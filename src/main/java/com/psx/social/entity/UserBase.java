package com.psx.social.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel("用户登录基本信息")
@Data
public class UserBase implements Serializable {
    private int id;
    private String user_account;
    private String user_pwd;
    private boolean auth;
    private String phone;
    private String email;
}

