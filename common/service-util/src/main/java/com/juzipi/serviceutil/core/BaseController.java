package com.juzipi.serviceutil.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.juzipi.commonutil.tool.PageTools;
import com.juzipi.commonutil.tool.Result;
import com.juzipi.commonutil.tool.ResultTools;
import com.juzipi.commonutil.util.StringUtils;

import java.util.Collection;


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
    protected Result judgmentResult(Object data){
        return StringUtils.isNotNull(data) ? ResultTools.successData(data) : ResultTools.fail();
    }


    /**
     * 判断数组
     * @param listData
     * @return
     */
    protected Result judgmentResult(Collection<?> listData){
        return StringUtils.isNotNull(listData) ? ResultTools.successData(listData) : ResultTools.fail();
    }


    /**
     * 判断多个字符串
     * @param stringData
     * @return
     */
    protected Result judgmentResult(String... stringData){
        return StringUtils.isNotEmpty(stringData) ? ResultTools.successData(stringData) : ResultTools.fail();
    }

    /**
     * 判断布尔
     * @param aBoolean
     * @return
     */
    protected Result booleanResult(Boolean aBoolean){
        return aBoolean ? ResultTools.success() : ResultTools.fail();
    }


    /**
     * 新增判断
     * @param rows
     * @return
     */
    protected Result toResult(Integer rows){
        return rows > 0 ? ResultTools.success() : ResultTools.fail();
    }


    /**
     * 判断分页
     * @param page
     * @return
     */
    protected Result pageResult(Page<?> page){
//        if (StringUtils.isNotEmpty(page.getRecords())){
//            PageResult pageResult = PageTool.getPageResult(page);
//            return ResultTool.successData(pageResult);
//        }
//        return ResultTool.fail();
        return StringUtils.isNotEmpty(page.getRecords()) ? ResultTools.successData(PageTools.getPageResult(page)) : ResultTools.fail();
    }
}
