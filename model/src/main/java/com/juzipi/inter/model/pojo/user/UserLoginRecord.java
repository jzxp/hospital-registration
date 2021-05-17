package com.juzipi.inter.model.pojo.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.juzipi.inter.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author juzipi
 * @Date 2021/5/17 18:30
 * @Info
 */
@ApiModel("用户登录登记表")
@Data
@TableName(value = "user_login_record")
public class UserLoginRecord extends BaseEntity {

    public static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "ip")
    private String ip;

}
