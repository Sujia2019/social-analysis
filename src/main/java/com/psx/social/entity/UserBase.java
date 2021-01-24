package com.psx.social.entity;

import lombok.Data;

@Data
public class UserBase {
    private int id;
    private String user_account;
    private String user_pwd;
    private boolean auth;
    private String phone;
    private String email;
}

