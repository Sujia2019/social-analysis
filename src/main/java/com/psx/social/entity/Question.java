package com.psx.social.entity;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@ApiModel("单个题目")
@Data
public class Question implements Serializable {

    private int id;//题号
    private String title;
    private String answer1;
    private String answer2;
    private String answer3;
}
