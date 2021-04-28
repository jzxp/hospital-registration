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




    public static PageResult getPageResult(Page<?> page){
        return new PageResult(page.getTotal(),page.getPages(),page.getRecords());
    }



}
