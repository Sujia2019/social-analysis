package com.psx.social.entity;

import lombok.Data;

@Data
public class UserDTO {
    private String user_account;
    private UserInfo userInfo;
    private UserMore userMore;
    private Settings settings;
}
