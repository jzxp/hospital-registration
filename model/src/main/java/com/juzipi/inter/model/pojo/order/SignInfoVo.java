package com.juzipi.inter.model.pojo.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author juzipi
 * @Date 2021/6/8 11:04
 * @Info
 */
@Data
public class SignInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "api基础路径")
    private String apiUrl;

    @ApiModelProperty(value = "签名秘钥")
    private String signKey;


}
