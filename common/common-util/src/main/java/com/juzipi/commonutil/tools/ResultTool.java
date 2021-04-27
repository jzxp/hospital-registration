package com.juzipi.commonutil.tools;


/**
 * @Author juzipi
 * @Date 2021/4/27 19:33
 * @Info 结果集封装的方法
 */
public class ResultTool {


    public static Result success(){
        return new Result(true);
    }


    public static Result fail(){
        return new Result(false);
    }


    public static Result successData(Object data){
        return new Result(true,data);
    }

    public static Result failData(Object data){
        return new Result(false,data);
    }


    public static Result successCustomize(ResultCode resultCode,Object data){
        return new Result(true,resultCode,data);
    }

    public static Result failCustomize(ResultCode resultCode,Object data){
        return new Result(false,resultCode,data);
    }


    public static Result successCustomize(ResultCode resultCode){
        return new Result(true,resultCode);
    }

    public static Result failCustomize(ResultCode resultCode){
        return new Result(false,resultCode);
    }

}
