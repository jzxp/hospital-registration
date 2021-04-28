package com.juzipi.commonutil.tool;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

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



}
