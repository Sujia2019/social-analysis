package com.psx.social.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@ApiModel("答题页")
@Data
public class QuestionPage {
    private int id;
    private String pageUrl; // 存答题文档的位置
    private String pageName; // 答题文档的名称
    private Date create_time;
    private Date modify_time;
}
