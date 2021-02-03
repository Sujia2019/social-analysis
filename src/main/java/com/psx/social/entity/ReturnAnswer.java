package com.psx.social.entity;

import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Data
public class ReturnAnswer {
    private int questionID;
    private int answerID;
    private int score;
}
