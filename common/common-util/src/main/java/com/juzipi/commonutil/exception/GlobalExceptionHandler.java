package com.juzipi.commonutil.exception;

import com.juzipi.commonutil.tool.Result;
import com.juzipi.commonutil.tool.ResultCode;
import com.juzipi.commonutil.tool.ResultTools;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;

/**
 * @Author juzipi
 * @Date 2021/4/29 9:40
 * @Info 全局异常处理
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {


    /**
     * 基础异常
     * @param e
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public Result customizeError(BaseException e){
        e.printStackTrace();
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("状态码:", e.getCode());
        linkedHashMap.put("简略错误信息", e.getMessage());
        linkedHashMap.put("请求参数", e.getArgs());
        linkedHashMap.put("错误所在类", e.getModule());
        return ResultTools.failCustomize(ResultCode.FAIL,linkedHashMap);
    }


    /**
     * excel抛出异常
     * @param e
     * @return
     */
    @ExceptionHandler(ExcelException.class)
    public Result excelError(ExcelException e){
        e.printStackTrace();
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("错误的操作:", e.getOption());
        linkedHashMap.put("错误所在位置:", e.getModule());
        linkedHashMap.put("简略的错误信息:", e.getMessage());
        return ResultTools.failCustomize(ResultCode.FAIL,linkedHashMap);
    }

    /**
     * 默认抛出异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result error(Exception e){
        e.printStackTrace();
        return ResultTools.failCustomize(ResultCode.ERROR,e.getMessage());
    }

}
