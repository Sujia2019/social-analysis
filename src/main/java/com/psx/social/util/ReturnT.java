package com.psx.social.util;

import lombok.Data;

@Data
public class ReturnT<T> {
    public static ReturnT<String> successReturn = new ReturnT<>(Constants.SUCCESS,Constants.SUCCESS_MSG);
    public static ReturnT<String> errorReturn = new ReturnT<>(Constants.FAIL,Constants.FAIL_MSG);
    private String message;
    private T obj;
    private int code;
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;
    /**
     * 记录总数
     */
    private long totalSize;
    /**
     * 页码总数
     */
    private int totalPages;
    public ReturnT(){

    }
    public ReturnT(T obj){
        this.obj = obj;
    }
    public ReturnT(int code,String message){
        this.code = code;
        this.message = message;
    }

    public ReturnT(int code,String message,T obj){
        this.code = code;
        this.message = message;
        this.obj = obj;
    }

    public ReturnT(int code,T obj){
        this.code = code;
        this.obj=obj;
    }

}
