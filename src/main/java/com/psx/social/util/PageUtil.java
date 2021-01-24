package com.psx.social.util;

import com.github.pagehelper.PageInfo;
import com.psx.social.entity.PageRequest;

import java.util.List;

public class PageUtil {

    /**
     * 将分页信息封装到统一的接口
     * @param pageInfo
     * @return
     */
    public static ReturnT<?> getPageResult(PageInfo<?> pageInfo) {
        ReturnT<List<?>> pageResult = new ReturnT<>();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setObj(pageInfo.getList());
        pageResult.setCode(Constants.SUCCESS);
        pageResult.setMessage("查询成功");
        return pageResult;
    }
}
