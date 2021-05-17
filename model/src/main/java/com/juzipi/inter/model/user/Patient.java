package com.juzipi.inter.model.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.juzipi.inter.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author juzipi
 * @Date 2021/5/17 17:24
 * @Info
 */
@Data
@ApiModel(value = "就诊人表")
@TableName(value = "patient")
public class Patient extends BaseEntity {

    public static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "证件类型")
    private String certificatesType;

    @ApiModelProperty(value = "证件编号")
    private String certificatesNo;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "电话号码")
    private String phoneNumber;

    @ApiModelProperty(value = "是否结婚")
    private Integer isMarry;

    @ApiModelProperty(value = "省编号")
    private String provinceCode;

    @ApiModelProperty(value = "市编号")
    private String cityCode;

    @ApiModelProperty(value = "区省编号")
    private String districtCode;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "联系人姓名")
    private String contactsName;

    @ApiModelProperty(value = "联系人证件类型")
    private String contactsCertificatesType;

    @ApiModelProperty(value = "联系人证件编号")
    private String contactsCertificatesNo;

    @ApiModelProperty(value = "联系人电话号码")
    private String contactsPhoneNumber;

    @ApiModelProperty(value = "就诊卡号")
    private String cardNo;

    @ApiModelProperty(value = "是否有医保")
    private Integer isInsure;

    @ApiModelProperty(value = "状态")
    private Integer status;


}
