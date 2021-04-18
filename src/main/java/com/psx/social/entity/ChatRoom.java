package com.psx.social.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ChatRoom {
    private String user_account;
    private long msg_count;
    private long activity_index;
    private Timestamp create_time;
    private Timestamp last_modify;
    private long positive;
    private long negative;
    private double positive_prob;
    private double neutral_prob;
    private double negative_prob;
}
