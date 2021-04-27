package com.juzipi.commonutil.tools;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.juzipi.inter.model.mode.PageBody;
import org.apache.poi.ss.formula.functions.T;

/**
 * @Author juzipi
 * @Date 2021/4/27 20:29
 * @Info
 */
public class PageTool {


    /**
     * 获取分页
     * @param entity 实体类
     * @param pageBody 分页请求体
     * @return page
     */
    public static Page getPage(T entity,PageBody pageBody){
        return new Page<T>(pageBody.getPageNum(), pageBody.getPageSize());
    }

    public static PageResult getPageResult(Page<?> page){
        return new PageResult(page.getTotal(),page.getPages(),page.getRecords());
    }

}
