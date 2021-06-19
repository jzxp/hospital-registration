package com.juzipi.inter.vo.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author juzipi
 * @Date 2021/6/19 17:32
 * @Info
 */
@Data
public class OrderSelectVo {

    @ApiModelProperty(value = "会员id")
    private Long userId;

    @ApiModelProperty(value = "订单交易号")
    private String outTradeNo;

    @ApiModelProperty(value = "就诊人id")
    private Long patientId;

    @ApiModelProperty(value = "就诊人")
    private String patientName;

    @ApiModelProperty(value = "医院名称")
    private String keyword;

    @ApiModelProperty(value = "订单状态")
    private String orderStatus;

    @ApiModelProperty(value = "安排日期")
    private String reserveDate;

    @ApiModelProperty(value = "创建开始时间")
    private String createTimeBegin;

    @ApiModelProperty(value = "创建结束时间")
    private String createTimeEnd;

}
