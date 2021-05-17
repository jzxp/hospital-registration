package com.juzipi.inter.model.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.juzipi.inter.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author juzipi
 * @Date 2021/5/17 17:35
 * @Info
 */
@ApiModel("用户登录登记表")
@Data
@TableName(value = "user_login_record")
public class UserLoginRecord extends BaseEntity {

    private Long userId;

    private String ip;

}
