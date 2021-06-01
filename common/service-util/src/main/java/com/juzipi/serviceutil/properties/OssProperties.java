package com.juzipi.serviceutil.properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author juzipi
 * @Date 2021/6/1 14:53
 * @Info
 */
public class OssProperties implements InitializingBean {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.secret}")
    private String secret;

    @Value("${aliyun.oss.bucket}")
    private String bucket;

    @Value("${aliyun.oss.path}")
    private String path;

    public static String ENDPOINT;

    public static String ACCESS_KEY_ID;

    public static String SECRET;

    public static String BUCKET;

    public static String PATH;

    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT = this.endpoint;
        SECRET = this.secret;
        ACCESS_KEY_ID = this.accessKeyId;
        BUCKET = this.bucket;
        PATH = this.path;
    }
}
