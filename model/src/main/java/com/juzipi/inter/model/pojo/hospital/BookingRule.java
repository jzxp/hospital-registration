package com.juzipi.inter.model.pojo.hospital;

import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @Author juzipi
 * @Date 2021/5/7 13:40
 * @Info
 */
@Data
@ApiModel(value = "预约规则")
@Document("bookingRule")
public class BookingRule {


    @ApiModelProperty(value = "预约周期")
    private Integer cycle;

    @ApiModelProperty(value = "放号时间")
    private String releaseTime;

    @ApiModelProperty(value = "停挂时间")
    private String stopTime;

    @ApiModelProperty(value = "退号将截至天数（退诊前一天为-1，当天为0）")
    private Integer quitDay;

    @ApiModelProperty(value = "退号时间")
    private String quitTime;

    @ApiModelProperty(value = "预约规则")
    private List<String> rule;


    public void setRule(String rule) {
        if(!StringUtils.isEmpty(rule)) {
            this.rule = JSONArray.parseArray(rule, String.class);
        }
    }

}
