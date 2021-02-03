package com.psx.social.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("留言信息")
@Data
public class BoardMsg {
    private String user_account;
    private String from_name;
    private String from_account;
    private String board_text;
    private String date_time;
}
