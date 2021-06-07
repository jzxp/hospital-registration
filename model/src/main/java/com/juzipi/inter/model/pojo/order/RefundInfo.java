package com.juzipi.inter.model.pojo.order;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.juzipi.inter.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * RefundInfo
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel("退款信息")
@TableName("refund_info")
public class RefundInfo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "对外业务编号")
	private String outTradeNo;

	@ApiModelProperty(value = "订单编号")
	private Long orderId;

	@ApiModelProperty(value = "支付类型（微信 支付宝）")
	private Integer paymentType;

	@ApiModelProperty(value = "交易编号")
	private String tradeNo;

	@ApiModelProperty(value = "退款金额")
	private BigDecimal totalAmount;

	@ApiModelProperty(value = "交易内容")
	private String subject;

	@ApiModelProperty(value = "退款状态")
	private Integer refundStatus;

	@ApiModelProperty(value = "回调时间")
	private Date callbackTime;

	@ApiModelProperty(value = "回调信息")
	private String callbackContent;

}

