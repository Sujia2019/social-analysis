package com.psx.social.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("提示")
@Data
public class Tips {
    private int count;
    private String account;
}
