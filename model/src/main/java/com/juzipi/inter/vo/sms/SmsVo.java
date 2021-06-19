package com.juzipi.inter.vo.sms;

import lombok.Data;

import java.util.Map;

/**
 * @Author juzipi
 * @Date 2021/6/10 20:06
 * @Info
 */
@Data
public class SmsVo {


    private String phone;


    private String templateCode;


    private Map<String,Object> param;

}
