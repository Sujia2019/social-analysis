package com.psx.social.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 735655488285535299L;

    private int id;
    private String user_account;
    private String sname;
    private int sage;
    private String phone;
    private String email;
    private String collage;
    private String major;
    private String birth;
    private int gender;

}
