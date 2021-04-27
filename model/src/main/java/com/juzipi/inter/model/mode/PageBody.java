package com.juzipi.inter.model.mode;

import lombok.Data;

/**
 * @Author juzipi
 * @Date 2021/4/27 20:23
 * @Info
 */
@Data
public class PageBody {


    private String key;

    private Long pageNum;

    private Long pageSize;

    //排序字段
    private String orderBy;


}
