package com.psx.social.entity;

import lombok.Data;

@Data
public class VerifyCode {
    private String phone;
    private String code;
}
