package com.juzipi.hospital;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author juzipi
 * @Date 2021/4/27 10:53
 * @Info 启动类
 */
@SpringBootApplication
@MapperScan(basePackages = "com.juzipi.hospital.mapper")
//跨模块配置文件不生效,扫描所有包
@ComponentScan("com.juzipi")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.juzipi")//找到服务调用
public class ServiceHospitalApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospitalApplication.class);
    }
}
