package com.psx.social.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("分析结果")
@Data
public class AnalyzingData implements Serializable {
    private int allCount;
    private double percent;
    private int finishedCount;
    private int countA;
    private int countB;
    private int countC;
    private int countD;
    private double percentA;
    private double percentB;
    private double percentC;
    private double percentD;
    @ApiModelProperty(">= 20000")
    private int activeA;
    @ApiModelProperty(">= 12000 & < 20000")
    private int activeB;
    @ApiModelProperty(">= 6000 & < 12000")
    private int activeC;
    @ApiModelProperty(">= 2000 & < 6000")
    private int activeD;
    @ApiModelProperty(">= 0 & < 2000")
    private int activeE;
}
