package com.juzipi.dictionary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author juzipi
 * @Date 2021/4/29 16:15
 * @Info 数据字典模块启动类
 */
@SpringBootApplication
@MapperScan("com.juzipi.dictionary.mapper")
@ComponentScan(basePackages = "com.juzipi")
public class ServiceDictionaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDictionaryApplication.class);
    }

}
