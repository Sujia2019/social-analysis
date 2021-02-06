package com.psx.social.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel("留言信息")
@Data
public class BoardMsg implements Serializable {
    private String user_account;
    private String from_name;
    private String from_account;
    private String board_text;
    private String date_time;
}
