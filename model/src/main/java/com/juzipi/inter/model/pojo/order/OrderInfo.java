package com.juzipi.inter.model.pojo.order;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.juzipi.inter.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author juzipi
 * @Date 2021/6/6 18:14
 * @Info
 */
@ApiModel("订单表")
@Data
@TableName("order_info")
public class OrderInfo extends BaseEntity {


    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "userId")
    private Long userId;

    @ApiModelProperty(value = "订单交易号")
    private String outTradeNo;

    @ApiModelProperty(value = "医院编号")
    private String hpCode;

    @ApiModelProperty(value = "医院名称")
    private String hpName;

    @ApiModelProperty(value = "科室编号")
    private String depCode;

    @ApiModelProperty(value = "科室名称")
    private String depName;

    @ApiModelProperty(value = "排班id")
    private String scheduleId;

    @ApiModelProperty(value = "医生职称")
    private String title;

    @ApiModelProperty(value = "安排日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date reserveDate;

    @ApiModelProperty(value = "安排时间（0：上午 1：下午）")
    private Integer reserveTime;

    @ApiModelProperty(value = "就诊人id")
    private Long patientId;

    @ApiModelProperty(value = "就诊人名称")
    private String patientName;

    @ApiModelProperty(value = "就诊人手机")
    private String patientPhone;

    @ApiModelProperty(value = "预约记录唯一标识（医院预约记录主键）")
    private String hpRecordId;

    @ApiModelProperty(value = "预约号序")
    private Integer number;

    @ApiModelProperty(value = "建议取号时间")
    private String fetchTime;

    @ApiModelProperty(value = "取号地点")
    private String fetchAddress;

    @ApiModelProperty(value = "医事服务费")
    private BigDecimal amount;

    @ApiModelProperty(value = "退号时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date quitTime;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;


}
