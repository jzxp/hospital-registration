package com.juzipi.inter.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author juzipi
 * @Date 2021/6/3 11:12
 * @Info
 */
@ApiModel("会员搜索对象")
@Data
public class UserInfoSelectVo {

    @ApiModelProperty(value = "关键字")
    private String keyword;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "认证状态")
    private Integer authStatus;

    @ApiModelProperty(value = "创建时间")
    private String createTimeBegin;

    @ApiModelProperty(value = "创建时间")
    private String createTimeEnd;


}
