package com.juzipi.serviceutil.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.juzipi.commonutil.tool.PageTools;
import com.juzipi.commonutil.tool.Result;
import com.juzipi.commonutil.tool.ResultTools;
import com.juzipi.commonutil.util.StringUtils;
import com.juzipi.serviceutil.util.HttpRequestHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;


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
        return StringUtils.isNotEmpty(listData) ? ResultTools.successData(listData) : ResultTools.failData("是空的");
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
        //如果不是空的
        return StringUtils.isEmpty(page.getRecords()) ? ResultTools.successData(PageTools.getPageResult(page)) : ResultTools.fail();
    }


    /**
     * 判断分页，pagehelper版
     * @param pageInfo
     * @return
     */
    protected Result pageResult(PageInfo<?> pageInfo){
//        if (StringUtils.isNotEmpty(page.getRecords())){
//            PageResult pageResult = PageTool.getPageResult(page);
//            return ResultTool.successData(pageResult);
//        }
//        return ResultTool.fail();
        //如果不是空的
        return pageInfo.getList().size() > 0 ? ResultTools.successData(PageTools.getPageResult(pageInfo)) : ResultTools.fail();
    }






}
