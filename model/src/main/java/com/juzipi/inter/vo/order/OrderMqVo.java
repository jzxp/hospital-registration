package com.juzipi.inter.vo.order;

import com.juzipi.inter.vo.sms.SmsVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author juzipi
 * @Date 2021/6/10 20:23
 * @Info
 */
@Data
public class OrderMqVo {

    @ApiModelProperty(value = "可预约数")
    private Integer reservedNumber;

    @ApiModelProperty(value = "剩余预约数")
    private Integer availableNumber;

    @ApiModelProperty(value = "排班id")
    private String scheduleId;

    @ApiModelProperty(value = "短信实体")
    private SmsVo smsVo;


}
