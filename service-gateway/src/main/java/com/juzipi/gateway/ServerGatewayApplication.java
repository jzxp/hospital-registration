package com.juzipi.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author juzipi
 * @Date 2021/5/13 18:00
 * @Info
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ServerGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerGatewayApplication.class);
    }

}
