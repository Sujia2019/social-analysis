package com.psx.social.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AddQuestionDTO implements Serializable {
    private String questionUrl;
    private List<Question> questionList;
    private List<String> res;
}
