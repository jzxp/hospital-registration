package com.juzipi.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author juzipi
 * @Date 2021/6/6 18:12
 * @Info
 */
@MapperScan("com.juzipi.order.mapper")
@ComponentScan(basePackages = "com.juzipi")
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class);
    }

}
