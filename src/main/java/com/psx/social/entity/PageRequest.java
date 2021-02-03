package com.psx.social.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("分页")
@Data
public class PageRequest {
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;
}
