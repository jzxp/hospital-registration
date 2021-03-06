package com.juzipi.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author juzipi
 * @Date 2021/5/17 16:49
 * @Info
 */
@SpringBootApplication(scanBasePackages = {"com.juzipi"})
@MapperScan("com.juzipi.user.mapper")
@EnableDiscoveryClient
@EnableFeignClients("com.juzipi")
public class ServiceUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class);
    }

}
