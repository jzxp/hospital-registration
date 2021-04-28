package com.juzipi.commonutil.tools;


import lombok.Data;

/**
 * @Author juzipi
 * @Date 2021/4/27 19:27
 * @Info Result 结果集
 */
@Data
public class Result {


    private Boolean flag;

    private Integer code;

    private String message;

    private Object data;

    //无返回值
    public Result(Boolean flag){
        //根据状态的true和false来判断成功或是失败的状态码和信息
        this.flag = flag;
        this.code = flag ? ResultCode.SUCCESS.getCode() : ResultCode.FAIL.getCode();
        this.message = flag ? ResultCode.SUCCESS.getMessage() : ResultCode.FAIL.getMessage();
    }


    //有返回值的
    public Result(Boolean flag,Object data){
        this.flag = flag;
        this.code = flag ? ResultCode.SUCCESS.getCode() : ResultCode.FAIL.getCode();
        this.message = flag ? ResultCode.SUCCESS.getMessage() : ResultCode.FAIL.getMessage();
        this.data = data;
    }



    //有返回值的，成功自定义，失败自定义
    public Result(Boolean flag,ResultCode resultCode,Object data){
        this.flag = flag;
        this.code = flag ? (resultCode == null ? ResultCode.SUCCESS.getCode() : resultCode.getCode()) : (resultCode == null ? ResultCode.FAIL.getCode() : resultCode.getCode());
        this.message = flag ? (resultCode == null ? ResultCode.SUCCESS.getMessage() : resultCode.getMessage()) : (resultCode == null ? ResultCode.FAIL.getMessage() : resultCode.getMessage());
        this.data = data;
    }


    //无返回值的，成功自定义，失败自定义
    public Result(Boolean flag,ResultCode resultCode){
        this.flag = flag;
        this.code = flag ? (resultCode == null ? ResultCode.SUCCESS.getCode() : resultCode.getCode()) : (resultCode == null ? ResultCode.FAIL.getCode() : resultCode.getCode());
        this.message = flag ? (resultCode == null ? ResultCode.SUCCESS.getMessage() : resultCode.getMessage()) : (resultCode == null ? ResultCode.FAIL.getMessage() : resultCode.getMessage());
    }

}