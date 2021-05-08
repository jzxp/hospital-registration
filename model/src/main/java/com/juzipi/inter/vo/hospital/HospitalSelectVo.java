package com.juzipi.inter.vo.hospital;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author juzipi
 * @Date 2021/5/8 18:13
 * @Info
 */
@Data
@ApiModel(value = "医院查询实体类")
public class HospitalSelectVo implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "医院编号")
    private String hpCode;

    @ApiModelProperty(value = "医院名称")
    private String hpName;

    @ApiModelProperty(value = "医院类型")
    private String hpType;

    @ApiModelProperty(value = "省code")
    private String provinceCode;

    @ApiModelProperty(value = "市code")
    private String cityCode;

    @ApiModelProperty(value = "区code")
    private String districtCode;

    @ApiModelProperty(value = "状态")
    private Integer status;

}
