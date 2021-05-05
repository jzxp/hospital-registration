package com.juzipi.hospitalmanage.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * OrderInfo
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "OrderInfo")
@TableName("order_info")
public class OrderInfo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "排班id")
	private Long scheduleId;

	@ApiModelProperty(value = "就诊人id")
	private Long patientId;

	@ApiModelProperty(value = "预约号序")
	private Integer number;

	@ApiModelProperty(value = "建议取号时间")
	private String fetchTime;

	@ApiModelProperty(value = "取号地点")
	private String fetchAddress;

	@ApiModelProperty(value = "医事服务费")
	private BigDecimal amount;

	@ApiModelProperty(value = "支付时间")
	private Date payTime;

	@ApiModelProperty(value = "退号时间")
	private Date quitTime;

	@ApiModelProperty(value = "订单状态")
	private Integer orderStatus;

}

