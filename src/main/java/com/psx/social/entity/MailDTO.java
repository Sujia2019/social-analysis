package com.psx.social.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("邮箱实体")
@Data
public class MailDTO {
    private String to;
    private String title;
    private String text;
}
