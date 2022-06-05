package com.luguosong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("com.luguosong.mapper")
@SpringBootApplication
public class NacosClientOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosClientOrderApplication.class, args);
    }

    /**
     * 通过@LoadBalanced配置负载均衡
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
