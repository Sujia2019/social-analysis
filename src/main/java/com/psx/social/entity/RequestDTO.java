package com.psx.social.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel("好友申请,用于列表界面展示，可再根据账号单独查信息")
@Data
public class RequestDTO {
    private String selfAccount;
    private String name;
    private String account;
    private String detail;
    @ApiModelProperty("表示好友关系状态，【-1】表示删除；【0】表示已发送申请待处理；【1】表示接受好友请求，已通过；【2】表示拒绝好友请求")
    private int addStatus;
}
