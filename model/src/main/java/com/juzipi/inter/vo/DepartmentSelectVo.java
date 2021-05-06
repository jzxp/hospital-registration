package com.juzipi.inter.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author juzipi
 * @Date 2021/5/6 8:46
 * @Info
 */

@Data
@ApiModel(value = "科室查询vo实体类")
public class DepartmentSelectVo {

    @ApiModelProperty(value = "医院编号")
    private String hpCode;

    @ApiModelProperty(value = "科室编号")
    private String depCode;

    @ApiModelProperty(value = "科室名称")
    private String depName;

    @ApiModelProperty(value = "大科室编号")
    private String bigCode;

    @ApiModelProperty(value = "医院大科室名称")
    private String bigName;


}
