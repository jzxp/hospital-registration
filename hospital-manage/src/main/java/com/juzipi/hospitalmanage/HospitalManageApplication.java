package com.juzipi.hospitalmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author juzipi
 * @Date 2021/5/5 10:10
 * @Info
 */
@SpringBootApplication
@MapperScan("com.juzipi.hospitalmanage.mapper")
public class HospitalManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalManageApplication.class);
    }
}
