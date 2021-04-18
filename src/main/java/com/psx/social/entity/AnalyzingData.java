package com.psx.social.entity;

import io.swagger.annotations.ApiModel;
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
    private int activeA;
    private int activeB;
    private int activeC;
    private int activeD;
}
