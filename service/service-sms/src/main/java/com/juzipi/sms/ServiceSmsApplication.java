package com.juzipi.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author juzipi
 * @Date 2021/5/19 18:11
 * @Info
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,scanBasePackages = "com.juzipi")
@EnableDiscoveryClient
public class ServiceSmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceSmsApplication.class);
    }

}
