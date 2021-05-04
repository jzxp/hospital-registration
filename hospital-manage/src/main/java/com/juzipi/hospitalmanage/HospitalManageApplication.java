package com.juzipi.hospitalmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.juzipi.hospitalmanage.mapper")
public class HospitalManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalManageApplication.class, args);
    }

}
