package com.juzipi.inter.model.pojo.hospital;

import com.juzipi.inter.model.base.BaseMongoEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author juzipi
 * @Date 2021/5/5 19:33
 * @Info 医生日程实体类
 */
@Data
@ApiModel(value = "医生日程表")
@Document("schedule")
public class Schedule extends BaseMongoEntity {

    @ApiModelProperty(value = "排班编号")
    @Indexed
    private String hpScheduleId;

    @ApiModelProperty(value = "医院编号")
    @Indexed
    private String hpCode;

    @ApiModelProperty(value = "科室编号")
    @Indexed
    private String depCode;

    @ApiModelProperty(value = "职称")
    private String title;

    @ApiModelProperty(value = "医生名字")
    private String docName;

    @ApiModelProperty(value = "技能")
    private String skill;

    @ApiModelProperty(value = "排班日期")
    private Date workDate;

    @ApiModelProperty(value = "排班时间")
    private Integer workTime;

    @ApiModelProperty(value = "排班编号")
    private Integer reservedNumber;

    @ApiModelProperty(value = "挂号费")
    //BigDecimal 表示一个任意大小且精度完全准确的浮点数。
    private BigDecimal amount;

    @ApiModelProperty(value = "排班状态（-1：停诊，0:停约，1：可约）")
    private Integer status;




}
