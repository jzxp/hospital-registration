package com.juzipi.inter.model.mode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author juzipi
 * @Date 2021/4/27 20:23
 * @Info
 */
@ApiModel(value = "分页请求体")
@Data
public class PageBody {


    @ApiModelProperty(value = "查询字段")
    private String key;

    @ApiModelProperty(value = "页码")
    private Long pageNum;

    @ApiModelProperty(value = "页面大小")
    private Long pageSize;

    @ApiModelProperty(value = "对象参数")
    private Object object;


}
