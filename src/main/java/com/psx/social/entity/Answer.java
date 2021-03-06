package com.psx.social.entity;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel("答案")
@Data
public class Answer implements Serializable {

    private int AnswerID;//答案号
    private String context;
    private int score;
    private boolean isChoose = false;
}
