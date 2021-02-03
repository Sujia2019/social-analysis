package com.psx.social.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("验证码")
@Data
public class VerifyCode {
    private String phone;
    private String code;
}
