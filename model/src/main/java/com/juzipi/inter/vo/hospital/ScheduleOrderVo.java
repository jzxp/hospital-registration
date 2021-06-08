package com.juzipi.inter.vo.hospital;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author juzipi
 * @Date 2021/6/8 10:19
 * @Info
 */
@Data
public class ScheduleOrderVo {


    @ApiModelProperty(value = "医院编号")
    private String hpCode;

    @ApiModelProperty(value = "医院名称")
    private String hpName;

    @ApiModelProperty(value = "科室编号")
    private String depCode;

    @ApiModelProperty(value = "科室名称")
    private String depName;

    @ApiModelProperty(value = "排班编号（医院自己的排班主键）")
    private String hpScheduleId;

    @ApiModelProperty(value = "医生职称")
    private String title;

    @ApiModelProperty(value = "安排日期")
    private Date reserveDate;

    @ApiModelProperty(value = "剩余预约数")
    private Integer availableNumber;

    @ApiModelProperty(value = "安排时间（0：上午 1：下午）")
    private Integer reserveTime;

    @ApiModelProperty(value = "医事服务费")
    private BigDecimal amount;

    @ApiModelProperty(value = "退号时间")
    private Date quitTime;

    @ApiModelProperty(value = "挂号开始时间")
    private Date startTime;

    @ApiModelProperty(value = "挂号结束时间")
    private Date endTime;

    @ApiModelProperty(value = "当天停止挂号时间")
    private Date stopTime;


}
