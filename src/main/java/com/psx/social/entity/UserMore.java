package com.psx.social.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserMore implements Serializable {
    private String user_account;
    private boolean IsFinishedQ;
    private int scores;
    private int msgCount;
    private int requestCount;
}
