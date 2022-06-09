package com.luguosong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("com.luguosong.mapper")
@SpringBootApplication
@EnableFeignClients
public class OpenFeignOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenFeignOrderApplication.class, args);
    }

}
