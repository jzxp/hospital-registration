package com.juzipi.inter.model.mode;

import lombok.Data;

/**
 * @Author juzipi
 * @Date 2021/5/17 17:50
 * @Info
 */
@Data
public class LoginBody {

    //微信openid
    private String openid;

    //手机号
    private String phoneNumber;

    //验证码
    private String code;

    //ip
    private String ip;

}
