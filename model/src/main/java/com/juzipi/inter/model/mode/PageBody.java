package com.juzipi.inter.model.mode;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author juzipi
 * @Date 2021/4/27 20:23
 * @Info
 */
@ApiModel(value = "分页请求体")
@Data
public class PageBody {


    private String key;

    private Long pageNum;

    private Long pageSize;

    //排序字段
    private String orderBy;


}
