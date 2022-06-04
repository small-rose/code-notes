package com.luguosong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("com.luguosong.mapper")
@SpringBootApplication
public class RestTemplateOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestTemplateOrderApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
