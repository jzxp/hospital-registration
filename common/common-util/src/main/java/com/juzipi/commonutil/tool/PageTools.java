package com.juzipi.commonutil.tool;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;

/**
 * @Author juzipi
 * @Date 2021/4/27 20:29
 * @Info
 */
public class PageTools {


    /**
     * 获取分页结果集
     * @param page
     * @return
     */
    public static PageResult getPageResult(Page<?> page){
        return new PageResult(page.getTotal(),page.getPages(),page.getRecords());
    }

    /**
     * 获取分页结果集 pagehelper版
     * @param pageInfo
     * @return
     */
    public static PageResult getPageResult(PageInfo<?> pageInfo){
        return new PageResult(pageInfo.getTotal(), (long) pageInfo.getPages(), pageInfo.getList());
    }



}
