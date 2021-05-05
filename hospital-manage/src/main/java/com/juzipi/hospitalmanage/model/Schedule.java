package com.juzipi.hospitalmanage.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * Schedule
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "Schedule")
@TableName("schedule")
public class Schedule extends BaseNoAutoEntity {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "医院编号")
	private String hpCode;

	@ApiModelProperty(value = "科室编号")
	private String depCode;

	@ApiModelProperty(value = "职称")
	private String title;

	@ApiModelProperty(value = "医生名称")
	private String docName;

	@ApiModelProperty(value = "擅长技能")
	private String skill;

	@ApiModelProperty(value = "安排日期")
	private String workDate;

	@ApiModelProperty(value = "安排时间（0：上午 1：下午）")
	private Integer workTime;

	@ApiModelProperty(value = "可预约数")
	private Integer reservedNumber;

	@ApiModelProperty(value = "剩余预约数")
	private Integer availableNumber;

	@ApiModelProperty(value = "挂号费")
	private String amount;

	@ApiModelProperty(value = "排班状态（-1：停诊 0：停约 1：可约）")
	private Integer status;
}

