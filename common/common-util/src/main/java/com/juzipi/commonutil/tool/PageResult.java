package com.juzipi.commonutil.tool;

import lombok.Data;

import java.util.List;

/**
 * @Author juzipi
 * @Date 2021/4/27 20:31
 * @Info
 */
@Data
public class PageResult {


    private Long total;//总条数
    private Long pages;//总页数
    private List<?> records;//每页数据集合
    private String sortBy;//排序


    public PageResult(Long total, Long pages, List<?> records) {
        this.total = total;
        this.pages = pages;
        this.records = records;
    }

}
