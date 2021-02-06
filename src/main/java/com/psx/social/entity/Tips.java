package com.psx.social.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel("提示")
@Data
public class Tips implements Serializable {
    private int count;
    private String account;
}
