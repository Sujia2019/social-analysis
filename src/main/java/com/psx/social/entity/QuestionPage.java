package com.psx.social.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel("问卷页")
@Data
public class QuestionPage implements Serializable {
    private int id;
    @ApiModelProperty("默认[],示例[1,2,3,4,5],int数组")
    private String questionList; // 存答题文档的位置
    private String pageName; // 答题文档的名称
    private boolean available;
}
