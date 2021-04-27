package com.juzipi.serviceutil.core;

import com.juzipi.commonutil.tools.Result;
import com.juzipi.commonutil.tools.ResultTool;
import com.juzipi.commonutil.utils.StringUtils;



/**
 * @Author juzipi
 * @Date 2021/4/27 19:49
 * @Info
 */
public class BaseController {


    /**
     * 判断对象
     * @param data Object
     * @return Result
     */
    protected Result Judgment(Object data){
        return StringUtils.isNotNull(data) ? ResultTool.successData(data) : ResultTool.fail();
    }


    /**
     * 判断布尔
     * @param aBoolean
     * @return
     */
    protected Result Judgement(Boolean aBoolean){
        return aBoolean ? ResultTool.success() : ResultTool.fail();
    }


    /**
     * 新增判断
     * @param rows
     * @return
     */
    protected Result toResult(Integer rows){
        return rows>0 ? ResultTool.success() : ResultTool.fail();
    }


}
