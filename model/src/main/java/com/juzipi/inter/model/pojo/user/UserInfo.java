package com.juzipi.inter.model.pojo.user;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.juzipi.inter.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author juzipi
 * @Date 2021/5/17 18:28
 * @Info
 */
@ApiModel(value = "用户表")
@Data
@TableName("user_info")
public class UserInfo extends BaseEntity {

    public static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "微信openid")
    private String openid;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "电话号码")
    private String phoneNumber;

    @ApiModelProperty(value = "用户真实姓名")
    private String name;

    @ApiModelProperty(value = "证件类型")
    private String certificatesType;

    @ApiModelProperty(value = "证件编号")
    private String certificatesNo;

    @ApiModelProperty(value = "证件路径")
    private String certificatesUrl;

    @ApiModelProperty(value = "认证状态")
    private Integer authStatus;

    @ApiModelProperty(value = "状态")
//    @TableField(fill = FieldFill.INSERT)
    private Integer status;

}
