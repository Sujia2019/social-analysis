package com.psx.social.entity;

import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;

@ApiIgnore
@Data
public class ReturnAnswer implements Serializable {
    private int questionID;
    private int answerID;
    private int score;
}
