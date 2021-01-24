package com.psx.social.entity;

import lombok.Data;

@Data
public class Settings {
    private int id;
    private String user_account;
    private boolean receive_friend;
    private boolean receive_board;
    private boolean show_board;
    private boolean send_notice;
}
