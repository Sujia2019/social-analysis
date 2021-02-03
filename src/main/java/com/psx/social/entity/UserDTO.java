package com.psx.social.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel("用户dto")
@Data
public class UserDTO implements Serializable {
    private String user_account;
    private UserInfo userInfo;
    private UserMore userMore;
    private Settings settings;
}
