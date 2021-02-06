package com.psx.social.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel("设置")
@Data
public class Settings implements Serializable {
    private int id;
    private String user_account;
    private boolean receive_friend;
    private boolean receive_board;
    private boolean show_board;
    private boolean send_notice;
}
