package com.juzipi.sms.service;

import com.juzipi.inter.vo.sms.SmsVo;

/**
 * @Author juzipi
 * @Date 2021/5/19 18:24
 * @Info
 */
public interface SmsService {

    Boolean sendCode(String phoneNumber, String captcha6);

    Boolean sendMQ(SmsVo smsVo);

}
