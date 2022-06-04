package com.luguosong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan("com.luguosong.mapper")
@SpringBootApplication
public class EurekaClientUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientUserApplication.class, args);
    }
}
