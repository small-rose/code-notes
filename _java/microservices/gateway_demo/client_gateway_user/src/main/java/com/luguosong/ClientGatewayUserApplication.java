package com.luguosong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.luguosong.mapper")
@SpringBootApplication
public class ClientGatewayUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientGatewayUserApplication.class, args);
    }
}
