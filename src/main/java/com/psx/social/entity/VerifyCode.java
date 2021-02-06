package com.psx.social.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel("验证码")
@Data
public class VerifyCode implements Serializable {
    private String phone;
    private String code;
}
