package com.juzipi.hospital;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author juzipi
 * @Date 2021/4/27 10:53
 * @Info 启动类
 */
@SpringBootApplication
@MapperScan("com.juzipi.hospital.mapper")
public class ServiceHospitalApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospitalApplication.class);
    }
}
