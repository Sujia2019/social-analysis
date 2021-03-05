package com.psx.social.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserQuestion implements Serializable {
    private String user_account;
    private String question_url;
    private String res_url;
}
