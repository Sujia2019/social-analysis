package com.psx.social.entity;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@ApiModel("答题问卷")
@Data
public class Question implements Serializable {

    private int QuestionID;//题号
    private String Title;
    private List<Answer> answers;
    private boolean isFinished = false;

}
