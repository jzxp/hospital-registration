package com.juzipi.serviceutil.properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author juzipi
 * @Date 2021/5/19 18:16
 * @Info
 */
public class SmsProperties implements InitializingBean {

    @Value("${aliyun.sms.regionId}")
    private String regionId;

    @Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.sms.secret}")
    private String secret;

    public static String REGION_ID;

    public static String ACCESS_KEY_ID;

    public static String SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        REGION_ID = this.regionId;
        ACCESS_KEY_ID = this.accessKeyId;
        SECRET = this.secret;
    }
}
