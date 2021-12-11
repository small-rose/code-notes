package cn.com.lgs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author luguosong
 * @date 2021/12/10 16:01
 */
@SpringBootApplication
@MapperScan("cn.com.lgs.mapper")
public class MybatisPlusHelloApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusHelloApplication.class,args);
    }
}
